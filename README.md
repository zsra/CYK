# cyk
CYK algorithm

In computer science, the Cocke–Younger–Kasami algorithm (alternatively called CYK, or CKY) is a parsing algorithm for context-free grammars, named after its inventors, John Cocke, Daniel Younger and Tadao Kasami. It employs bottom-up parsing and dynamic programming.

The standard version of CYK operates only on context-free grammars given in Chomsky normal form (CNF). However any context-free grammar may be transformed to a CNF grammar expressing the same language (Sipser 1997). 

----Info------

Grammar format must be starts with the Base Symbol like S. 
Grammar Format:

|Non-Terminal| -> |Non-Terminal||Non-Terminal|
or 
|Non-Terminal| -> |Terminal|
