echo "ANTLR Generating..."
java  org.antlr.v4.Tool tiger.g4

echo "Compile Start..."

#javac  Tiger*.java -d ./classes/
javac *.java 

#echo "running testcases"
#rm test_results/*

#for i in {1..10}
#do 
#    echo "test${i}.tig is processing"
#    java CheckSymbols "tests/test${i}.tig" > "test_results/test${i}.txt"
#done

#echo "done"

#rm tiger*.java 
#rm tiger*.tokens
#rm *.class

#echo "file deleted..."

