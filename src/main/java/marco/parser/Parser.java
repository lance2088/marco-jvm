package marco.parser;

import marco.lang.MarcoBlock;
import marco.lang.exceptions.MarcoException;
import marco.parser.antlr.MarcoParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.io.InputStream;

public class Parser {
    private static Parser parser;

    private Parser() {
    }

    public MarcoBlock parse(String code) {
        ANTLRInputStream antlrInputStream = new ANTLRInputStream(code);

        return parseANTLRInputStream(null, antlrInputStream);
    }

    public MarcoBlock parse(String fileName, InputStream in) {
        ANTLRInputStream antlrInputStream;
        try {
            antlrInputStream = new ANTLRInputStream(in);
        } catch (IOException e) {
            throw new MarcoException("inputstream error");
        }

        return parseANTLRInputStream(fileName, antlrInputStream);
    }

    private MarcoBlock parseANTLRInputStream(String fileName, ANTLRInputStream antlrInputStream) {
        Lexer lexer = new Lexer(antlrInputStream);

        lexer.removeErrorListeners();
        lexer.addErrorListener(new ExceptionErrorListener());

        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        MarcoParser parser = new MarcoParser(tokenStream);

        parser.removeErrorListeners();
        parser.addErrorListener(new ExceptionErrorListener());

        ParseTree tree = parser.file();

        ParseTreeVisitor visitor = new ParseTreeVisitor(fileName);
        visitor.visit(tree);

        return visitor.getResult();
    }

    public static Parser instance() {
        if (parser == null) {
            parser = new Parser();
        }

        return parser;
    }
}
