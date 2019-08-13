import sys, os , hashlib
import time

def get_function_list() : 
	funclist = {}
	for segea in Segments() :
		for funcea in Functions(segea, SegEnd(segea)) :
			functionName = GetFunctionName(funcea)
			asms = ""

			for (startea, endea) in Chunks(funcea) :
				for head in Heads(startea, endea) :
					if idc.SegName(head) == ".text" : 
						asms += GetDisasm(head)

			if asms != "" :	funclist.update({functionName : [head,hashlib.sha1(asms).hexdigest()]})
			
	return funclist

def func() :
	funclist = get_function_list()
	return funclist

def decompile___(address) :
	time.sleep(0.3)
	idc.RunPlugin("hexx64", 0)
	time.sleep(0.4)
	c = decompile(get_func(address))
	for idx,v in enumerate(c.lvars) :
		v.name = "value"+str(idx)
	return str(c)


def arg_read() : 
	k = open(os.path.dirname(__file__)+"/args","r")
	arg = k.read()
	k.close()
	return arg

if len(sys.path) > 15 : #mans if not current process is ida
	from idautils import *
	from idaapi import *
	from idc import *
	try : 
		idaapi.autoWait()
		idc.Wait()
		arg = arg_read().split(" ")

		if arg[0] == "func" : 
			funclist = func()
			os.system("mkdir ./result 2>/dev/null")
			open("./result/funclist","w+").write(str(funclist))

		if arg[0] == "decompile" :
			address = int(arg[1])
			result = decompile___(address)
			os.system("mkdir ./result 2>/dev/null")
			open("./result/decompile","w+").write(str(result))

	except Exception as e : 
		open("./error","w+").write(str(e))

	qexit(0)
