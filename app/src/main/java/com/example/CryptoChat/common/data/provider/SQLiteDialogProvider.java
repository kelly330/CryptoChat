package com.example.CryptoChat.common.data.provider;


import android.util.Log;

import com.example.CryptoChat.common.data.models.DaoSession;
import com.example.CryptoChat.common.data.models.Dialog;
import com.example.CryptoChat.common.data.models.DialogDao;

import java.util.ArrayList;
import java.util.List;

/*
 * TODO: Implement singleton pattern with getInstance() method
 * */
public class SQLiteDialogProvider {
    public static SQLiteDialogProvider instance;
    private DialogDao dialogDao;
    private DaoSession session;

    public static SQLiteDialogProvider getInstance(DaoSession session){
        if (instance == null) {
            synchronized (SQLiteMessageProvider.class) {
                if (instance == null) {
                    instance = new SQLiteDialogProvider();
                    instance.dialogDao = session.getDialogDao();
                    instance.session = session;
                }
            }
        }
        return instance;
    }

    public void addDialog(Dialog dialog) {
        dialogDao.insert(dialog);
    }

    public List<Dialog> getDialogs() {
        List<Dialog> l = dialogDao.queryBuilder()
                .list();
        Log.v("SQLiteDialogProvider", l.toString());
        return l;
    }

    public Dialog getDialogById(String Id) {
        Dialog d = dialogDao.queryBuilder()
                .where(DialogDao.Properties.Id.eq(Id))
                .uniqueOrThrow();
        return d;
    }

    public Dialog getDialogByReceiverId(String receiverId) {
        Dialog d = dialogDao.queryBuilder()
                .where(DialogDao.Properties.ReceiverId.eq(receiverId)).uniqueOrThrow();
        return d;
    }

    public Dialog dropDialog(String dialogId){
        Dialog d = dialogDao.queryBuilder()
                .where(DialogDao.Properties.Id.eq(dialogId)).uniqueOrThrow();
        return d;
    }

    public void updateDialog(Dialog dialog) {
        dialogDao.update(dialog);
    }

    public void clear() {
        dialogDao.deleteAll();
    }


}
