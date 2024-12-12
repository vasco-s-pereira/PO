make:
	find xxl -name "*.java" | xargs javac -cp .:../lib/po-uilib.jar

clean:
	find xxl -name "*.class" | xargs rm -f
