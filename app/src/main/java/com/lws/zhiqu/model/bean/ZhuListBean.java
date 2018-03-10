package com.lws.zhiqu.model.bean;

import java.util.List;

/**
 * Created by song on 2018/2/6.
 */

public class ZhuListBean {
    @Override
    public String toString() {
        return "ZhuListBean{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                ", top_stories=" + top_stories +
                '}';
    }

    /**
     * date : 20180205
     * stories : [{"images":["https://pic1.zhimg.com/v2-ae468ae8036ac30f430c19ac0b767488.jpg"],"type":0,"id":9669523,"ga_prefix":"020522","title":"小事 · 过年就是这个味儿"},{"images":["https://pic2.zhimg.com/v2-a46cd199f627819c72f0e3388a0a635d.jpg"],"type":0,"id":9670044,"ga_prefix":"020521","title":"这部讲孙悟空的美术片，可能只有很少的人看过"},{"images":["https://pic3.zhimg.com/v2-0f446500b896ace7cc61978a00adb8ca.jpg"],"type":0,"id":9668885,"ga_prefix":"020519","title":"那些让人咽口水、但「仅供参考」的图片，都是怎么来的？"},{"images":["https://pic3.zhimg.com/v2-6ad195a0397fd684b5f4d12a025a5f42.jpg"],"type":0,"id":9669379,"ga_prefix":"020518","title":"怎么玩好「狼人」？我拿出了最全面的总结"},{"images":["https://pic1.zhimg.com/v2-5206035bd73e6d6b14b86783d32780c0.jpg"],"type":0,"id":9669040,"ga_prefix":"020517","title":"- 有哪些常识性错误？\r\n- 影视剧里的失忆"},{"images":["https://pic4.zhimg.com/v2-d742c52d9a843cd3af94145ba058eedb.jpg"],"type":0,"id":9669666,"ga_prefix":"020516","title":"爸妈，我可是你们亲生的，为什么总是贬低我？"},{"images":["https://pic2.zhimg.com/v2-c0778758106be36aba1e3dbecf813579.jpg"],"type":0,"id":9669715,"ga_prefix":"020516","title":"考研 3 次，都失败了，我想说\u2026\u2026"},{"images":["https://pic3.zhimg.com/v2-82fce9a5c966e75c48414bfc6968b65a.jpg"],"type":0,"id":9669633,"ga_prefix":"020514","title":"为什么我们走路时脚跟先着地，猫却是脚尖？"},{"images":["https://pic2.zhimg.com/v2-ae91aee15c1bd8febf0d383a0d638c7d.jpg"],"type":0,"id":9669482,"ga_prefix":"020512","title":"大误 · 我看见一具熟悉的尸体朝我走来"},{"images":["https://pic1.zhimg.com/v2-eb9a804779798fb5b5a645056c2fa800.jpg"],"type":0,"id":9667321,"ga_prefix":"020510","title":"你 60 分已经不错了，我可不行，最少也要 90"},{"images":["https://pic1.zhimg.com/v2-f78806d65e8f7d653706cb4154649414.jpg"],"type":0,"id":9668854,"ga_prefix":"020509","title":"为什么公司更喜欢提升福利，而不是直接给员工涨薪？"},{"images":["https://pic4.zhimg.com/v2-9efa6f1cb4c676dc6f1454aa9e22e4f7.jpg"],"type":0,"id":9669365,"ga_prefix":"020508","title":"Facebook 用户时长减少了 5000 万小时，扎克伯格对此还挺满意"},{"images":["https://pic4.zhimg.com/v2-9e20e68fe20ca01e20f082c671c70d2f.jpg"],"type":0,"id":9669334,"ga_prefix":"020507","title":"独占 95% 的下载量，《旅行青蛙》为什么能在中国爆红？"},{"images":["https://pic3.zhimg.com/v2-1ebade5945437e5953b916313b3cff0e.jpg"],"type":0,"id":9669585,"ga_prefix":"020507","title":"父母「为爱鼓掌」时，记得锁门"},{"images":["https://pic1.zhimg.com/v2-f1e07deae30e13f8f98d6c01ad6834e8.jpg"],"type":0,"id":9669229,"ga_prefix":"020507","title":"阿里巴巴已经「大而不能倒」了吗？"},{"images":["https://pic3.zhimg.com/v2-980d80fde6377c69d01ab8d46a87337e.jpg"],"type":0,"id":9669524,"ga_prefix":"020506","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic1.zhimg.com/v2-e1b144eed11984e7177a41791c02053c.jpg","type":0,"id":9669715,"ga_prefix":"020516","title":"考研 3 次，都失败了，我想说\u2026\u2026"},{"image":"https://pic3.zhimg.com/v2-efad27d808d64b32d6f6f73069f85906.jpg","type":0,"id":9669633,"ga_prefix":"020514","title":"为什么我们走路时脚跟先着地，猫却是脚尖？"},{"image":"https://pic1.zhimg.com/v2-4eb01d457265c0c804b1193863ba2690.jpg","type":0,"id":9669666,"ga_prefix":"020516","title":"爸妈，我可是你们亲生的，为什么总是贬低我？"},{"image":"https://pic3.zhimg.com/v2-cb689c298b1dfb3712b69899f531d49e.jpg","type":0,"id":9669378,"ga_prefix":"020416","title":"《杀破狼》里只能用五年的人工心脏，现实中有可能长期使用吗？"},{"image":"https://pic3.zhimg.com/v2-980c005c81868333cbdad279966905ca.jpg","type":0,"id":9669585,"ga_prefix":"020507","title":"父母「为爱鼓掌」时，记得锁门"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * images : ["https://pic1.zhimg.com/v2-ae468ae8036ac30f430c19ac0b767488.jpg"]
         * type : 0
         * id : 9669523
         * ga_prefix : 020522
         * title : 小事 · 过年就是这个味儿
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        @Override
        public String toString() {
            return "StoriesBean{" +
                    "type=" + type +
                    ", id=" + id +
                    ", ga_prefix='" + ga_prefix + '\'' +
                    ", title='" + title + '\'' +
                    ", images=" + images +
                    '}';
        }
    }

    public static class TopStoriesBean {
        /**
         * image : https://pic1.zhimg.com/v2-e1b144eed11984e7177a41791c02053c.jpg
         * type : 0
         * id : 9669715
         * ga_prefix : 020516
         * title : 考研 3 次，都失败了，我想说……
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
