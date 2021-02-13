package com.example.plantwebapp.utilities

import android.content.Context

class FileHelper {
    companion object {
//        fun getTextFromResources(context: Context, resourceId: Int): String {
//            return context.resources.openRawResource(resourceId).use {
//                it.bufferedReader().use {
//                    it.readText()
//                }
//            }
//        }

        // Opens and reads the .json file in the Assets folder
        fun getTextFromAssets(context: Context, fileName: String): String {
            return context.assets.open(fileName).use {
                it.bufferedReader().use {
                    it.readText()
                }
            }
        }
    }
}