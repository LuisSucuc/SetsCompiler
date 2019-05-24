package Principal;

import java.util.List;


public class Response {
    public List<String> inputs;
    public List<String> responses;
    public List<Token> tokens;
    public boolean success;

    public Response(List<String> inputs, List<String> responses, boolean success) {
        this.inputs = inputs;
        this.responses = responses;
        this.success = success;
    }
    
    public Response(List<String> inputs, List<String> responses, List<Token> tokens, boolean success) {
        this.inputs = inputs;
        this.responses = responses;
        this.tokens = tokens;
        this.success = success;
    }
}
