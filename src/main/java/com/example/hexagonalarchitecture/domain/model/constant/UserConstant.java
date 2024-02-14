package com.example.hexagonalarchitecture.domain.model.constant;

public class UserConstant {
    public static final String TASK_NOT_FOUND_MESSAGE_ERROR = "No se encontro una tarea con el id %s";
    public static final String USER_NOT_FOUND_MESSAGE_ERROR = "No se encontro un Usuario con el id %s";

    public static final String CODE_NOT_FOUND_MESSAGE_ERROR = "Codigo invalido %s";

    public static final String CODE_VERIFIED = "Cuenta verificada %s";

    public static final String CODE_NOT_VERIFIED = "Cuenta no verificada %s";

    public static final String SUBJECT_MAIL = "Verificar cuenta API TEST ";

    public static final String BODY_MAIL = "Para verificar la cuenta en API TEST abra el siguiente link -> ";
    public static final String CURRENT_USER_NOT_ALLOW_TO_DO_TASKS = "El total de horas (%s) en tareas supera las horas permitidas";
    public static final String CURRENT_USER_NOT_ALLOW_TO_DELETE = "El usuario %s no se puede eliminar porque tiene tareas pendientes por finalizar";
}
