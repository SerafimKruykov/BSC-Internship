package com.example.note

class Constants {

    /**
     * Константы DetailsFragment
     */
    object DetailsView{
        /**
         * Константа для интента расшеривания заметки
         */
        const val INTENT_TEXT_TYPE : String  = "text/plain"
    }

    /**
     * Константы открытия ViewPagerActivity
     */
    object Transaction{
        /**
         * Передает позицию ViewPager2 при открытии заметки
         */
        const val PASS_ACTION = "position"
        /**
         * Передает информацию о режиме открытия ViewPagerActivity
         */
        const val ADD_ACTION = "isAdding"
    }

    /**
     * Константы для работы с сетью
     */
    object Api{
        /**
         * Базовый Url для запросов
         */
        const val BASE_URL = "https://jsonplaceholder.typicode.com"
        /**
         * Конечная точка запросов
         */
        const val URL_TARGET = "posts/10"
    }

    /**
     * Константы для бродкаста
     */
    object Broadcast{
        /**
         * Intent tag
         */
        const val TAG_HEADER = "BroadcastHeader"
        const val TAG_CONTENT = "BroadcastContent"
        const val ACTION = "com.skbsc.broadcastnote"
    }

    /**
     * Константы для анимации CustomTextView
     */
    object Animation{
        const val DURATION: Long = 1000
        const val REPEAT_COUNT = 1
        const val START = 0f
        const val FINISH = 360f
    }

    /**
     * Константы для определения местоположения
     */
    object Location{
        const val REQUEST_CODE = 101
    }
}