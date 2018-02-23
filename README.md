# Plagiarism-Checker
Program that checks plagiarism between 2 text files. One target document is compared to a corpus file or a main document. The user chooses a number which will be used to parse the document. For example if the user enters 2, then the plagiarism checker will return that there is plagiarism and where it is if there are 2 words next to each other in both docouments that are exactly the same. 

I have a parser that takes a text file and separates it into a sequence of its n words in their given order (with duplicates included) in O(n) time. It is considered an act of plagiarism if any student uses a sequence of m words (in their given order) from a document.
