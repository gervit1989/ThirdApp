package com.skillbranch.thirdapp.mvp.models;

/**
 * Абстракция реализующая набор методов для получения и изменения данных (network,
 * database, shared Preferences, memory) . Как правило используется паттерн «репозиторий»
 */
public class SplashModel {
    /**
     * Конструктор
     */
    public SplashModel() {
    }

    /**
     *  проверить есть ли сохраненный токен авторизации
     * @return Да/нет
     */
    public boolean isAuthUser(){
        // TODO: 22-10-2016 search token in SP
        return false;
    }

    /**
     * запрос на авторизацию у сервера
     * @param email email пользователя
     * @param password пароль для входа
     */
    public void loginUser(String email, String password){
        // TODO: 22-10-2016 send data to server for auth
    }
}
