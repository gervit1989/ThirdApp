package com.skillbranch.thirdapp.mvp.views;

public interface IView {
    /**
     * Показать сообщение
     * @param message сообщение
     */
    void showMessage(String message);

    /**
     * Показать ошибку
     * @param e - ошибка
     */
    void showError(Throwable e);

    /**
     * Показать загрузку
     */
    void showLoad();

    /**
     * Скрыть загрузку
     */
    void hideLoad();
}
