package se.iths.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

public class StudentDataInvalidException extends RuntimeException {


}

@Provider
class StudentDataInvalidExceptionMapper implements ExceptionMapper<StudentDataInvalidException> {

    @Override
    public Response toResponse(StudentDataInvalidException e) {
        return Response.status(418).build();
    }
}

