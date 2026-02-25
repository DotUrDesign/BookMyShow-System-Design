package com.SystemDesign.BookMyShow.DTOs;

import com.SystemDesign.BookMyShow.DTOs.enums.ResponseStatus;
import com.SystemDesign.BookMyShow.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpResponseDTO {
    private User user;
    private ResponseStatus responseStatus;
}
