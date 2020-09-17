import re
import os.path


# Scrapes Mardown
def convert(x):
    if re.search("{#}", x) is not None:
       st =  x.split(" ", 1)
       return "\\section{" + st[1] + "}"
   if re.search("{##}", x) is not None:
       st = x.split(" ", 1)
       return "\\subsection{" + st[1] + "}"
   if re.search("{###}", x) is not None:
       st = x.split(" ", 1)
       return "\\subsubsection{" + st[1] + "}"

file = open("Software Requirements Document.md", "r")
past = False
doc = []
for line in file:
    print(line)
    if(past is False):
        if(line == "# Introduction"): #Switch to regex
            past = True
    else:
        doc.append(line)
file.close()
if !exists('Software Requirements Document.tex'):
    file.open('Software Requirements Document.tex', "w+")
    file.write("\\documentclass[12pt]{article}\n\\title{Software Requirements Document}\n\\author{Cut-and-Paste}\n\\begin{document}\n")
    for x in doc:
        file.write(convert(x))
    file.close()
else:
    # TODO
print(doc)


        
    
    
