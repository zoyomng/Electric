package com.dtelec.core.mvvm.base;

interface IModel {

    /**
     * ViewModel销毁时清除Model,与ViewModel共存亡.Model层同样不能持有长生命周期对象
     */
    void onCleared();
}
