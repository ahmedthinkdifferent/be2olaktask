package com.ahmedagamy.task.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;

import com.ahmedagamy.task.R;

import dmax.dialog.SpotsDialog;

/**
 * Created by ahmedagamy on 9/10/2017.
 */

public class DialogUtils {


    public static AlertDialog showNoInternetDialog(Context context, DialogInterface.OnClickListener buttonListener) {
        return createDialog(context, R.string.operation_failed, R.string.no_internet_connection,
                context.getString(R.string.ok), buttonListener, null, null);
    }


    private static AlertDialog createDialog(Context context, @StringRes int title,
                                            @StringRes int message, String positiveButtonText,
                                            DialogInterface.OnClickListener positiveClickListener,
                                            String negativeButtonText, DialogInterface.OnClickListener negativeClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title).setMessage(message);
        if (positiveClickListener != null) {
            builder.setPositiveButton(positiveButtonText, positiveClickListener);
        }
        if (negativeClickListener != null) {
            builder.setNegativeButton(negativeButtonText, negativeClickListener);
        }
        return builder.create();
    }

    public static SpotsDialog createProgress(Context context) {
        return createSpotProgressDialog(context, context.getString(R.string.please_wait));
    }

    private static SpotsDialog createSpotProgressDialog(Context context, String text) {
        SpotsDialog spotsDialog = new SpotsDialog(context, text);
        spotsDialog.setCancelable(false);
        return spotsDialog;
    }


    public static AlertDialog showErrorDialog(Context context , @StringRes int message ,
                                              DialogInterface.OnClickListener buttonListener){
        return createDialog(context, R.string.operation_failed, message,
                context.getString(R.string.ok), buttonListener, null, null);
    }
}
