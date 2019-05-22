package Principal;

import java.util.List;


public class Response {
    public List<String> inputs;
    public List<String> responses;
    public boolean success;

    public Response(List<String> inputs, List<String> responses, boolean success) {
        this.inputs = inputs;
        this.responses = responses;
        this.success = success;
    }
}
