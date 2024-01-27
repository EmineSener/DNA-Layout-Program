# DNA Layout Creation Project
## Introduction

This project includes an algorithm that works on DNA sequences and combines these sequences. The project is specifically designed for use in areas such as genomic data analysis and DNA assembly. Using the overlap matrix, similarities between DNA sequences are identified and these sequences are combined within a broader context.

## Description
* The project can randomly generate the DNA sequence of the desired length. It can also work with user-specified DNA sequence according to the user's choice.
* DNA subsequences are created from any number of random sections from the initial DNA sequence. Since the algorithm also requires complement sequences, the complement of each sequence created is also created.
* The project creates an overlap matrix on given DNA sequences. This matrix shows overlapping regions. Match and mismatch scores are determined by the user to determine overlapping regions. Thus, how the algorithm works for different match scores can be tested.
* You can examine the [Overlap Matrix](./overlapMatrisi.txt) .
* Overlaps between sequences are reviewed and optimized. Regions exceeding a certain overlap level are merged. The overlap level is obtained from the user so that the algorithm can be tested with different threshold values.
*The project produces a DNA assembly consisting of joined DNA sequences.
