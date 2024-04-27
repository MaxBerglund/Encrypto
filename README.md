# Encrypto 1.0
Encrypto is an encryption tool developed by Max Berglund, Abood Aldaker and Daniel Öhman.



Planning before starting the project:
-------------------------------------
We plan to create a tool that encrypts and decrypts text according to some encyption algorithm, perhaps Data Encryption Standard (DES) which is a symmetric encryption algoritm, which means that is uses the same key for both encryption and decryption. Use of DES begun in 1977, but it is no longer used because of its security vulnerabilities. This makes it a decent size project for us to implement, and perhaps give us the possibility to start learn about realistic ways to crack the encryption without the key. Minimally Viable Product (MVP) (Not to be confused with Most Valuable Player: Abood Aldaker 🙇‍♂️🏆👑) : https://encode-decode.com/des-encrypt-online/

We will probably use Java to create the program, but it could be interesting and useful to use Go for this project because the DES algorithm generates 16 different keys and goes through 16 processes which might be independent of eachother, and could therefore be parallelized.

We have some additional features and goals which we might implement if there is additional time and effort available:
1. Make a GUI interface for Encrypto, using some sort of Framework (probably JavaSwing).
2. A method to encrypt files of different types instead of just strings, like image or PDF encryption.
3. An AI bot that attempts to decrypt an encrypted message without access to the key using different cryptanalysis techniques. Inspiration 1: https://blog.daisie.com/cryptanalysis-techniques-beginners-guide-tips/#brute-force-technique 



Plan for development:
---------------------
The first thing that we need to do is to really understand how the DES encryption algorithm works. Once we have done that, we need to decide on how we will organise our files and setup the project. 
- Incredibly unclear and unexpectedly confusing tutorial by indian man: https://www.youtube.com/watch?v=Q5p0WIa8S0o
- Better tutorial by another indian man, but is instead almost an hour long: https://www.youtube.com/watch?v=-j80aA8q_IQ
- The tutorial we're actually going to use: https://www.geeksforgeeks.org/data-encryption-standard-des-set-1/

We plan to divide the project up into a number of branches:

  1. **Permutation Functions** This function takes in a 64-bit string and returns a different permutation of that string. This branch will consists of both the initial and final Permutation functions. A good way of doing this is by making the permutation order into an array of ints, and making it so that the first position of the array corresponds to the first position of the input. Let us say that the first element of the array is 6. Then the first position of the input is swapped with the element in the 6:th position in the input. Then it goes on until it has swapped all elements according to the permutation order.
  2. **String Divider** This function takes in a 64-bit string and divides it into two half blocks, each of which consits of 32 bits. If a string is longer than 64-bit, it divides it up into several parts that are each 64-bit and does the same process (divides them up into two 32-bit strings) before sending them into the Encrypto Machine. 
  3. **Encrypto Machine** This will probably be the largest branch, and it will include the main encryption process that runs 16 iterations and performs the key transformation, expansion permutation, S-box permutation, P-box permutation and the XOR&Swap. We plan to divide these parts into sub-branches for this branch.
      1. *Key Transformation* The initial 64-bit key is transformed into a 56-bit key by discarding every 8th bit of the initial key. Then there are 16 rounds run of a process that generates a new 48-bit subkey for each round. Each subkey is divided into two 28-bit halves, and these halves are circularly shifted left by one or two positions depending on which round it is. One shift if round number is 1, 2, 9 or 16. Two shifts otherwise.
      2. *Expansion Permutation* The two different 32-bit halves from the the plain input text are expanded to 48-bit. This happens as one of the 32-bit halves is divided into 8 blocks, each of which consisting of 4 bits. Each 4-bit block is then expanded to a corresponding 6-bit block in order to realize the expansion of the half of the plain input text.
      3. *S-box Permutation* ...
      4. *P-box permutation* ...
      5. *XOR&Swap* ...

Features:
1. Encrypt and decrypt text.
2. Engaging GUI interface [MAYBE]
3. Encrypt splendid images [MAYBE]
4. Encrypt mindblowing PDFs [MAYBE]
5. Decrypto God [MAYBE]
