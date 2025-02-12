package fr.epita.assistants.common.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class MoveResponse {
    private int posX;
    private int posY;
}
