import os , sys , time
import ida_utils

class IDA_Conext() : 
	def __init__(self) :
		self.IDA_PATH = ""
		self.HEX_RAY_CFG_PATH = ""

IDA = IDA_Conext()

PY_PATH = os.path.abspath(ida_utils.__file__)

if PY_PATH[-1] == "c" : PY_PATH = PY_PATH[:-1] #pyc

class Binary() : 
	def __init__(self , path) : 
		self.path = path
		self.abspath = os.path.abspath(path)

		if IDA.IDA_PATH == "" :
			print "SET IDA PATH!"
			sys.exit(-1)

		self.set_arg("func")
		self.run()
		time.sleep(0.4)
		self.funclist = eval(open("./result/funclist","r").read()) #funclist.update({functionName : [head,hashlib.sha1(asms).hexdigest()]})

	def run(self) :
		os.system('"{ida_path}" -c -A -S"{py_path} {bin_path}" {bin_abspath} 1>/dev/null 2>/dev/null'.format(ida_path=IDA.IDA_PATH, py_path=PY_PATH, bin_path=self.path , bin_abspath=self.abspath))

	def decompile(self , decompilelist) :
		decompileresult = {}
		if IDA.HEX_RAY_CFG_PATH != "" :
			z = open(IDA.HEX_RAY_CFG_PATH,"r")
			originalcfg = z.read()
			k = open("./utils_/hex_ray_.cfg").read()
			z = open(IDA.HEX_RAY_CFG_PATH,"w+")
			z.write(k)

		for func in decompilelist :
			self.set_arg("decompile " + str(self.funclist[func][0])) #address
			self.run()
			k = open("./result/decompile","r")
			decompileresult.update({func:k.read()})

		if IDA.HEX_RAY_CFG_PATH != "" :
			k = open(IDA.HEX_RAY_CFG_PATH,"w+").write(originalcfg)

		return decompileresult

	def set_arg(self,arg) :
		k = open(os.path.dirname(__file__)+"/args","w+")
		k.write(arg)
		k.close()


#def decompile(b1,b2) : 
	#PY_PATH = 
	#
