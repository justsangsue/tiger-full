import java.io.*;

public class IRStream {

    BufferedReader br;
    String path;
    boolean isFunc;
    String funcString;

    public IRStream(String path) throws FileNotFoundException {
        this.path = path;
        br = new BufferedReader(new FileReader(path));
    }

    public IRStream(String str, boolean isFunc) {
        this.isFunc = isFunc;
        this.funcString = str;

        Reader inputString = new StringReader(str);
        BufferedReader reader = new BufferedReader(inputString);
        this.br = reader;
    }

    public IRStream nextFunction() throws Exception {
        String line = br.readLine();
        if (line == null || line.trim() == "") {
            return null;
        }

        while (!line.startsWith("#start_function") && line != null) {
            line = br.readLine();
        }

        line = br.readLine();
        String functionString = "";

        while(!line.startsWith("#end_function")) {
            functionString += line + "\n";
            line = br.readLine();
        }

        return new IRStream(functionString, true);
    }

    public String next() throws Exception {
        String line = br.readLine();

        while (line != null && (line.equals("") || line.charAt(0) == '#')) {
            line = br.readLine();
        }

        if (line != null) {
            return line.replaceAll("\\s+$", ""); // remove whitespaces only from right side
        } else {
            return line;
        }
    }

    public void reset() throws FileNotFoundException {
        if (this.isFunc) {
            Reader inputString = new StringReader(this.funcString);
            BufferedReader reader = new BufferedReader(inputString);
            this.br = reader;
        } else {
            this.br = new BufferedReader(new FileReader(path));
        }
    }

    public IRStream cloneStream() throws FileNotFoundException {
        if (this.isFunc) {
            return new IRStream(this.funcString, true);
        } else {
            return new IRStream(this.path);
        }
    }

    public void close() throws IOException {
        this.br.close();
    }
}
