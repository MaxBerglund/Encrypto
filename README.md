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
- Superb tutorial, but is almost an hour long: https://www.youtube.com/watch?v=-j80aA8q_IQ
- The tutorial we're actually going to use: https://www.geeksforgeeks.org/data-encryption-standard-des-set-1/

We plan to divide the project up into a number of branches:

  1. **String Format Function** This function takes in a string and turns it into binary and divides it into 64-bit blocks so that the several 64-length arrays are ready for input into the Encrypto Machine. It therefore returns an array of arrays containing 64 ints and each int is 1 digit of the binary string. The last part is filled with zero.
  2. **Permutation Functions** This function takes in a 64-length array of ints and swaps all elements according to the the Initial Permutation tabel (IP). It then returns this permuted version of the array. This branch will consists of both the initial and final Permutation functions.
  3. **Encrypto Machine** This will probably be the largest branch, and it will include the main encryption process that runs 16 iterations and performs the key transformation, expansion permutation, S-box permutation, P-box permutation and the XOR&Swap. We plan to divide these parts into sub-branches for this branch.
      1. *Key Transformation* The initial is turned into hexadecimal, then converted into a 64-bit binary string that is stored as a 64-length array of ints where each int is a digit of the binary string. Then the binary version is transformed into a 56-length array key by discarding every 8th bit of the initial key while permutating over the PC-1 table. Then there are 16 rounds run of a process that generates 16 new 48-length array subkey, one for each round. Each subkey is divided into two 28-length array halves, C and D, These halves are both circularly shifted left, using the left shift operator <<, by one or two positions depending on which round it is. One shift if round number is 1, 2, 9 or 16. Two shifts otherwise. For the first round, we get the two partial keys C1 and D1 that have both been left shifted once. We should combine these two into one key again, C1D1, and permutate over it according to the PC-2 table. The result of that permutation gives us the first subkey, K1! Then we do that for each round to get all of the 16 different subkeys that are each 48-length arrays.
      2. *Expansion Permutation* The received input from the initial permutation, that is a 64-length array, is divided into two 32-length halves, left L and right R. Then for each round, an recursive function is run. For round n, the left Ln is made from using the previous right Rn-1, and the right Rn is made from using the previous left Ln-1 combined with the output of a function f (with the inputs Rn-1 and a key Kn). The function f expands its input (Rn-1) to a 48-length array using a selection table that repeats some of the bits. The next step of the function is an XOR Swap with the key Kn, wehre each element from the expanded input and the key are compared and adjusted according to the XOR logical operation "Either or...". Then it is time for the S-boxes.
      4. *S-box Permutation* See from 30:30 on the good tutorial...
      5. *P-box permutation* ...

Features:
1. Encrypt and decrypt text.
2. Engaging GUI interface [MAYBE]
3. Encrypt splendid images [MAYBE]
4. Encrypt mindblowing PDFs [MAYBE]
5. Decrypto God [MAYBE]
