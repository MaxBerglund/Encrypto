# Encrypto 1.0
Encrypto is an encryption tool developed by Max Berglund and Abood Aldaker.



Planning before starting the project:
-------------------------------------
We plan to create a tool that encrypts and decrypts text according to some encyption algorithm, perhaps Data Encryption Standard (DES) which is a symmetric encryption algoritm, which means that is uses the same key for both encryption and decryption. Use of DES begun in 1977, but it is no longer used because of its security vulnerabilities. This makes it a decent size project for us to implement, and perhaps give us the possibility to start learn about realistic ways to crack the encryption without a key. More information on DES: https://www.geeksforgeeks.org/data-encryption-standard-des-set-1/ 

We will probably use Java to create the program, but it could be interesting and useful to use Go for this project because the DES algorithm generates 16 different keys and goes through 16 processes which might be independent of eachother, and could therefore be parallelized.

We have some additional features and goals which we might implement if there is additional time and effort available:
1. A method to encrypt files of different types instead of just strings, like image or PDF encryption.
2. An AI bot that attempts to decrypt an encrypted message without access to the key using different cryptanalysis techniques. Inspiration 1: https://blog.daisie.com/cryptanalysis-techniques-beginners-guide-tips/#brute-force-technique 



Plan for development:
---------------------
The first thing that we need to do is to really understand how the DES encryption algorithm works. Once we have done that, we need to decide on how we will organise our files and setup the project. Incredibly unclear and unexpectedly confusing tutorial by indian man: https://www.youtube.com/watch?v=Q5p0WIa8S0o The tutorial we're actually going to use: https://www.geeksforgeeks.org/data-encryption-standard-des-set-1/

We plan to divide the project up into a number of branches:

  1. **Permutation functions** This function takes in a 64-bit string and returns a different permutation of that string. This branch will consists of both the initial and final Permutation functions. A good way of doing this is by making the permutation order into an array of ints, and making it so that the first position of the array corresponds to the first position of the input. Let us say that the first element of the array is 6. Then the first position of the input is swapped with the element in the 6:th position in the input. Then it goes on until it has swapped all elements according to the permutation order.
  2. **String divider** This function takes in a 64-bit string and divides it into two half blocks, each of which consits of 32 bits.
  3. **EncryptoMachine** This will probably be the largest branch, and it will include the main encryption process that runs 16 iterations and performs the key transformation, expansion permutation, S-box permutation, P-box permutation and the XOR&Swap. These will probably be divided into sub-branches for this branch.

Features:
1. Encrypt text.
2. Encrypt images [MAYBE]
3. Encrypt PDFs [MAYBE]
4. Decrypto [MAYBE]

Week 1 milestones:
1. Permutation functions are functional.
2. The string divider is functional.
3. EncryptoMachine is mostly completed.

Week 2 milestones:
1. EncryptoMachine is functional.
2. ...
