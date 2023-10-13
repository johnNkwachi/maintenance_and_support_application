package arnergy.solar.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ApiResponse {
    private ZonedDateTime timestamp;
    private boolean isSuccessful;
    private Object data;
    private int status;
    private String path;
}
