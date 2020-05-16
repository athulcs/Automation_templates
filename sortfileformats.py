import os
import shutil
format="."+input("Enter file format:")
path=os.getcwd()
if(not os.path.exists(path+"\\Docs")):
    os.mkdir("Sorted")
for x in os.listdir():
    file_name, file_extension = os.path.splitext(x)
    if(str(file_extension).lower()==format):
        src=os.path.join(path,x)
        shutil.copy(src,path+"\\Docs")
