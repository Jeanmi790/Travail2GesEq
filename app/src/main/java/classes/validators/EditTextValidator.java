package classes.validators;

import android.service.autofill.RegexValidator;
import android.widget.EditText;

import com.example.travail2geseq.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;
import java.util.regex.Pattern;

public class EditTextValidator {

    TextInputEditText tietToValidate;
    int minLength;
    int maxLength;
    Boolean canBeEmpty;
    Boolean canContainOnlyLetters;
    String[] messagesError = new String[3]; // 0 = minLength, 1 = maxLength, 2 = canBeEmpty

    public EditTextValidator(TextInputEditText tietToValidate, int minLength, int maxLength, Boolean canBeEmpty,Boolean canContainOnlyLetters, String[] messagesError) {
        this.tietToValidate = tietToValidate;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.canBeEmpty = canBeEmpty;
        this.canContainOnlyLetters = canContainOnlyLetters;
        this.messagesError = messagesError;
    }

    public int ValidateMinLength() {
        if (Objects.requireNonNull(tietToValidate.getText()).toString().length() < minLength) {
            tietToValidate.setError(messagesError[0]);
            return 1;
        }
        return 0;
    }

    public int ValidateMaxLength() {
        if (Objects.requireNonNull(tietToValidate.getText()).toString().length() > maxLength) {
            tietToValidate.setError(messagesError[1]);
            return 1;
        }
        return 0;
    }
    public int ValidateEmpty() {
        if (Objects.requireNonNull(tietToValidate.getText()).toString().isEmpty() && !canBeEmpty) {
            tietToValidate.setError(messagesError[2]);
            return 1;
        }
        return 0;
    }
    public int ValidateInput(){
        if (!tietToValidate.getText().toString().matches("^[a-zA-Z]*$")&& canContainOnlyLetters) {
            tietToValidate.setError(messagesError[3]);
            return 1;
        }
        return 0;

    }
    public void ResetError() {
        tietToValidate.setError(null);
    }
    public void ResetInput(){
        tietToValidate.setText("");
    }

    public int returnNumberOfErrors() {
        int result = 0;
        result += ValidateMinLength();
        result += ValidateMaxLength();
        result += ValidateEmpty();
        result += ValidateInput();
        return result;
    }

}
