numberOfCharacters = int(raw_input("Number of characters: "));

fileName = str(numberOfCharacters) + "_Character_Words.txt";

resultsFile = open(fileName, 'w');

with open("all_words.txt", 'r') as wordFile:
    for line in wordFile:
        for word in line.split():
            if len(word) == numberOfCharacters:
                resultsFile.write(word + "\n");

resultsFile.close();
print("Finished");

