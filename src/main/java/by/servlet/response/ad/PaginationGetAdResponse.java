package by.servlet.response.ad;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PaginationGetAdResponse {
    List<GetAdResponse> responses;
}
