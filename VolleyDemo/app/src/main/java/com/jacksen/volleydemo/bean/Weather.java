package com.jacksen.volleydemo.bean;

import java.util.List;

/**
 * Created by Admin on 2016/12/26.
 */

public class Weather {

    /**
     * desc : OK
     * status : 1000
     * data : {"wendu":"3","ganmao":"昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。","forecast":[{"fengxiang":"南风","fengli":"微风级","high":"高温 3℃","type":"阴","low":"低温 -7℃","date":"26日星期一"},{"fengxiang":"南风","fengli":"微风级","high":"高温 1℃","type":"晴","low":"低温 -7℃","date":"27日星期二"},{"fengxiang":"北风","fengli":"3-4级","high":"高温 3℃","type":"多云","low":"低温 -7℃","date":"28日星期三"},{"fengxiang":"南风","fengli":"微风级","high":"高温 0℃","type":"晴","low":"低温 -6℃","date":"29日星期四"},{"fengxiang":"南风","fengli":"微风级","high":"高温 1℃","type":"多云","low":"低温 -7℃","date":"30日星期五"}],"yesterday":{"fl":"微风","fx":"南风","high":"高温 3℃","type":"霾","low":"低温 -2℃","date":"25日星期日"},"aqi":"30","city":"北京"}
     */

    private String desc;
    private int status;
    private DataEntity data;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public static class DataEntity {
        /**
         * wendu : 3
         * ganmao : 昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。
         * forecast : [{"fengxiang":"南风","fengli":"微风级","high":"高温 3℃","type":"阴","low":"低温 -7℃","date":"26日星期一"},{"fengxiang":"南风","fengli":"微风级","high":"高温 1℃","type":"晴","low":"低温 -7℃","date":"27日星期二"},{"fengxiang":"北风","fengli":"3-4级","high":"高温 3℃","type":"多云","low":"低温 -7℃","date":"28日星期三"},{"fengxiang":"南风","fengli":"微风级","high":"高温 0℃","type":"晴","low":"低温 -6℃","date":"29日星期四"},{"fengxiang":"南风","fengli":"微风级","high":"高温 1℃","type":"多云","low":"低温 -7℃","date":"30日星期五"}]
         * yesterday : {"fl":"微风","fx":"南风","high":"高温 3℃","type":"霾","low":"低温 -2℃","date":"25日星期日"}
         * aqi : 30
         * city : 北京
         */

        private String wendu;
        private String ganmao;
        private YesterdayEntity yesterday;
        private String aqi;
        private String city;
        private List<ForecastEntity> forecast;

        public String getWendu() {
            return wendu;
        }

        public void setWendu(String wendu) {
            this.wendu = wendu;
        }

        public String getGanmao() {
            return ganmao;
        }

        public void setGanmao(String ganmao) {
            this.ganmao = ganmao;
        }

        public YesterdayEntity getYesterday() {
            return yesterday;
        }

        public void setYesterday(YesterdayEntity yesterday) {
            this.yesterday = yesterday;
        }

        public String getAqi() {
            return aqi;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public List<ForecastEntity> getForecast() {
            return forecast;
        }

        public void setForecast(List<ForecastEntity> forecast) {
            this.forecast = forecast;
        }

        public static class YesterdayEntity {
            /**
             * fl : 微风
             * fx : 南风
             * high : 高温 3℃
             * type : 霾
             * low : 低温 -2℃
             * date : 25日星期日
             */

            private String fl;
            private String fx;
            private String high;
            private String type;
            private String low;
            private String date;

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getFx() {
                return fx;
            }

            public void setFx(String fx) {
                this.fx = fx;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }
        }

        public static class ForecastEntity {
            /**
             * fengxiang : 南风
             * fengli : 微风级
             * high : 高温 3℃
             * type : 阴
             * low : 低温 -7℃
             * date : 26日星期一
             */

            private String fengxiang;
            private String fengli;
            private String high;
            private String type;
            private String low;
            private String date;

            public String getFengxiang() {
                return fengxiang;
            }

            public void setFengxiang(String fengxiang) {
                this.fengxiang = fengxiang;
            }

            public String getFengli() {
                return fengli;
            }

            public void setFengli(String fengli) {
                this.fengli = fengli;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }
        }
    }
}
