import os, sys
from utils_.utils import *

IDA.IDA_PATH = '/Applications/IDA Pro 7.0/ida64.app/Contents/MacOS/ida64' #required
IDA.HEX_RAY_CFG_PATH = "/Applications/IDA Pro 7.0/ida.app/Contents/MacOS/cfg/hexrays.cfg" #not required


def diffing(b1path,b2path) : 
	binary1 = Binary(b1path)
	binary2 = Binary(b2path)

	decompilelist = []

	for func in binary1.funclist.keys() :
		if binary1.funclist[func][1] != binary2.funclist[func][1] : #compare assembly
			decompilelist.append(func)

	print "[*] diffing target functions.."
	print decompilelist
	b1decompilelist = binary1.decompile(decompilelist)
	b2decompilelist = binary2.decompile(decompilelist)

	os.system("rm -rf ./result 2>/dev/null")
	os.system("mkdir ./result 2>/dev/null")

	for func in b1decompilelist.keys() :
		open("./utils_/adec.c","w+").write(b1decompilelist[func])
		open("./utils_/bdec.c","w+").write(b2decompilelist[func])
		os.system("vimdiff ./utils_/adec.c ./utils_/bdec.c -c 'syntax off'  -c TOhtml -c 'w! ./result/{name}.html' -c 'qa!'".format(name=func))
		print("SAVED ./result/{name}.html ..".format(name=func))
		print("./result/{name}.html ..".format(name=func))



if __name__ == '__main__' : 
	if len(sys.argv) != 3 :
		print "USAGE : python ./main.py binary1 binary2"
		sys.exit(1)

	print("[*] analysing....")
	b1 = sys.argv[1]
	b2 = sys.argv[2]
	diffing(b1,b2)

