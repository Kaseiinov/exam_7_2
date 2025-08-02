package kg.attractor.exam_7.service;

import kg.attractor.exam_7.exceptions.ErrorResponseBody;
import org.springframework.validation.BindingResult;

public interface ErrorService {
    ErrorResponseBody makeResponse(BindingResult bindingResult);
}
