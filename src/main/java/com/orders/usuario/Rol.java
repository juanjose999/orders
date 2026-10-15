package com.orders.usuario;

public enum Rol {
    /** Administra un gimnasio: ve y gestiona todos sus datos. */
    ADMIN,

    ANONIMOUS,
    /** Cliente del gimnasio: accede con su correo y solo consulta su propia información. */
    CLIENT
}
