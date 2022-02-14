package uz.pdp.lesson7.payload;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Responce {
    private String message;
    private Boolean success;
    private Object object;

    // false bo'lganda qaytadigan javob!
    public Responce(String message, Boolean success) {
        this.message = message;
        this.success = success;
    }
}




//  .   ____          _            __ _ _
//          /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
//          ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
//          \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
//          '  |____| .__|_| |_|_| |_\__, | / / / /
//          =========|_|==============|___/=/_/_/_/