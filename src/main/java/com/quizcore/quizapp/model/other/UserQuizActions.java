package com.quizcore.quizapp.model.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserQuizActions  {
    boolean videoUploaded;
    boolean paymentDone;
    boolean quizStarted;
    boolean quizSubmitted;
}
