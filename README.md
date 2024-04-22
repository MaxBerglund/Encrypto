# Encrypto
Encrypto is an encryption tool developed by Max Berglund and Abood Aldaker.



Planning before starting the project:
-------------------------------------
We plan to create a tool that encrypts and decrypts text according to some encyption algorithm, perhaps Data Encryption Standard (DES) which is a symmetric encryption algoritm, which means that is uses the same key for both encryption and decryption. Use of DES begun in 1977, but it is no longer used because of its security vulnerabilities. This makes it a decent size project for us to implement, and perhaps give us the possibility to start learn about realistic ways to crack the encryption without a key. More information on DES: https://www.geeksforgeeks.org/data-encryption-standard-des-set-1/ 

We will probably use Java to create the program, but it could be interesting and useful to use Go for this project because the DES algorithm generates 16 different keys and goes through 16 processes which might be independent of eachother, and could therefore be parallelized.

We have some additional features and goals which we might implement if there is time and effort available:
1. A method to encrypt files of different types instead of just strings, like image or PDF encryption.
2. An AI bot that attempts to decrypt an encrypted message without access to the key using different cryptanalysis techniques. Inspiration 1: https://blog.daisie.com/cryptanalysis-techniques-beginners-guide-tips/#brute-force-technique 



Plan for development:
---------------------
The first thing that we plan to develop is the encryption algorithm, closely followed by its related decryption algorithm so that we can effectively test our implementation. It might be a good idea to split these into two separate branches. (?)...

Features:
1. ...

Milestones:
1. ...

[TODO]
