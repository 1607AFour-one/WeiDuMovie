package com.bw.movie.bean;

public class DetailsData {

    /**
     * result : {"address":"海淀区中关村广场购物中心津乐汇三层（鼎好一期西侧）","businessHoursContent":"星期一 至 星期日 早09:00:00 - 晚22:00:00","commentTotal":0,"distance":0,"followCinema":2,"id":12,"logo":"http://172.17.8.100/images/movie/logo/mjhlyc.jpg","name":"美嘉欢乐影城中关村店","phone":"010-59863777","vehicleRoute":"中关村站下车：运通 105、106、109、110、113中关村南站：特4、特6、302、307、320、323、332、355、365、466、697、717、718、731、732、735、737中关村西站：26、47、333、384、740、751、913、944、982、983"}
     * message : 查询成功
     * status : 0000
     */

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class ResultBean {
        /**
         * address : 海淀区中关村广场购物中心津乐汇三层（鼎好一期西侧）
         * businessHoursContent : 星期一 至 星期日 早09:00:00 - 晚22:00:00
         * commentTotal : 0
         * distance : 0
         * followCinema : 2
         * id : 12
         * logo : http://172.17.8.100/images/movie/logo/mjhlyc.jpg
         * name : 美嘉欢乐影城中关村店
         * phone : 010-59863777
         * vehicleRoute : 中关村站下车：运通 105、106、109、110、113中关村南站：特4、特6、302、307、320、323、332、355、365、466、697、717、718、731、732、735、737中关村西站：26、47、333、384、740、751、913、944、982、983
         */

        private String address;
        private String businessHoursContent;
        private int commentTotal;
        private int distance;
        private int followCinema;
        private int id;
        private String logo;
        private String name;
        private String phone;
        private String vehicleRoute;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getBusinessHoursContent() {
            return businessHoursContent;
        }

        public void setBusinessHoursContent(String businessHoursContent) {
            this.businessHoursContent = businessHoursContent;
        }

        public int getCommentTotal() {
            return commentTotal;
        }

        public void setCommentTotal(int commentTotal) {
            this.commentTotal = commentTotal;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public int getFollowCinema() {
            return followCinema;
        }

        public void setFollowCinema(int followCinema) {
            this.followCinema = followCinema;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getVehicleRoute() {
            return vehicleRoute;
        }

        public void setVehicleRoute(String vehicleRoute) {
            this.vehicleRoute = vehicleRoute;
        }
    }
}
