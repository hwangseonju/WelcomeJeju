<!-- 카드에 CSS 입힌 후 -->
<template>
  <!-- vuetify gird 시스템 이해하기 
  레퍼런스 1 : https://chansnotes.github.io/2019/07/27/vuetify-grid-%EC%8B%9C%EC%8A%A4%ED%85%9C-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0/
  레퍼런스 2 : https://ayoteralab.tistory.com/entry/Vuejs-10-use-v-form -->
  <v-container fluid>
    <v-layout row wrap>
      <v-row dense>
        <v-col
          cols="12"
          md="4"
          sm="6"
          v-for="(detail, index) in details"
          v-bind:key="index"
        >
          <v-card
            style="border-radius: 20px; background: rgb(142, 207, 255)"
            class="round-box mx-auto"
            max-width="400"
          >
            <v-card-subtitle
              class="pb-0"
              style="font-weight: bold; margin-top: 10px; margin-bottom: 10px"
            >
              {{ details[index].start_at }} ~ {{ details[index].end_at }}
            </v-card-subtitle>
            <v-card-title class="card-erea center-row text-style">
              {{ details[index].traval_title }}
            </v-card-title>
            <v-card-actions class="center-row">
              <!-- 오늘 날짜: {{ today }} <br />
              여행 끝난 날짜 : {{ details[index].end_at }} -->
              <!-- <div v-if="isFinished[index]"> -->
              <!-- <goto-plan></goto-plan> -->
              <v-btn
                v-if="isFinished[index]"
                @click="gotoPlan"
                style="
                  border-radius: 20px;
                  font-weight: bold;
                  font-size: large;
                  padding-left: 96px;
                  padding-right: 96px;
                  padding-top: 20px;
                  padding-bottom: 20px;
                  margin-top: 4px;
                "
                class="small-erea"
                text
              >
                계획 보러가기
              </v-btn>
              <!-- </div> -->
              <!-- <div > -->
              <!-- <goto-review></goto-review> -->
              <v-btn
                v-else
                @click="gotoReview"
                style="
                  border-radius: 20px;
                  font-weight: bold;
                  font-size: large;
                  padding-left: 96px;
                  padding-right: 96px;
                  padding-top: 20px;
                  padding-bottom: 20px;
                  margin-top: 4px;
                "
                class="small-erea"
                text
              >
                방문기 보러가기
              </v-btn>
              <!-- </div> -->
            </v-card-actions>
          </v-card>
        </v-col>
        <v-col cols="12" md="4" sm="6">
          <plan-plus></plan-plus>
        </v-col>
      </v-row>
    </v-layout>
  </v-container>
</template>
<script>
// testJSON : https://blog.naver.com/PostView.naver?blogId=jogilsang&logNo=222504849580&parentCategoryNo=69&categoryNo=&viewDate=&isShowPopularPosts=true&from=search
// import PlanPage from "@/components/plan/PlanPlus.vue";
// import gotoPlan from "@/components/list/button/gotoPlan.vue";
// import gotoReview from "@/components/list/button/gotoReview.vue";
import PlanPlus from "@/components/list/PlanPlus.vue";
import dayjs from "dayjs";
// import getList from "@/api/apiUtils2.js";

// import { mapActions, mapGetters } from "vuex";
// import axios from "axios";
// axios.defaults.baseURL =
//   process.env.VUE_APP_BACKEND_DOMAIN + ":" + process.env.VUE_APP_BACKEND_PORT;
// const API_BASE_URL = "/api";
// const travel = "travel";

import testJSON from "@/components/list/plan_data.json";
const details = testJSON;

export default {
  name: "PlanCard",
  components: {
    PlanPlus,
    // gotoPlan,
    // gotoReview,
  },
  data: function () {
    return {
      index: "",
      details,
      isFinished: [],
      // items: [],
    };
  },
  created() {
    this.calcDate();
  },
  mounted() {
    // this.setItems();
  },
  computed: {
    // ...mapGetters(travel, ["details"]),
    // setItems: () => {
    //   this.items = this.details;
    // },
  },
  methods: {
    // ...mapActions(travel, ["FETCH_LIST"]),
    // ...mapMutations(travel, ["setToken"]),

    /** 버튼 이벤트 등록 */
    gotoPlan: function () {
      console.log("계획 보러가기 버튼을 눌렀습니다");
      this.$router.push({ path: "/plan" });
    },
    gotoReview: function () {
      console.log("방문기 보러가기 버튼을 눌렀습니다");
      this.$router.push({ path: "/review" });
    },
    /** 여행 완료 여부 판단 */
    calcDate: function () {
      const size = this.details.length;
      console.log(size);
      const nowday = dayjs().format("YYYY-MM-DD");
      for (let index = 0; index < size; index++) {
        let endday = dayjs(this.details[index].end_at);
        console.log("nowday: " + nowday);
        console.log("endday: " + this.details[index].end_at);
        console.log(dayjs(nowday).diff(endday));
        // diff 가 - 이면 오늘 로부터 이후
        // diff 가 + 이면 오늘 로부터 이전
        // console.log(this.details[index].end_at);
        // console.log(this.nowday.isBefore(endday));
        if (dayjs(nowday).diff(endday) < 0) {
          console.log("끝나지 않은 여행입니다");
          this.isFinished[index] = true;
        } else {
          console.log("끝난 여행입니다");
          this.isFinished[index] = false;
        }
        console.log(this.isFinished[index]);
      }
    },
    // getTravelList() {
    //   return axios({
    //     method: "GET",
    //     url: API_BASE_URL + "/travel",
    //     // headers: {
    //     //   "Content-Type": "application/json",
    //     //   Authorization: "Bearer " + ACCESS_TOKEN,
    //     // },
    //   })
    //     .then((response) => {
    //       this.details = response;
    //       // const { data } = response;
    //       // console.log(data);
    //       // this.details.push = data;
    //     })
    //     .catch((error) => {
    //       alert(error);
    //       console.log(error);
    //     });
    // },
  },
};
</script>
<style scped>
.center-row {
  place-content: center;
}
.round-box {
  width: 90%;
  height: 90%;
  text-align: center;
  background: rgb(142, 207, 255);
  border-radius: 20px;
  margin: 20px;
}
.text-style {
  font-weight: bold;
  font-size: x-large;
}
.card-erea {
  background: rgb(255, 202, 67);
  border-radius: 20px;
  margin-left: 10px;
  margin-right: 10px;
  padding: 15px;
}
.small-erea {
  background: rgb(255, 202, 67);
  /* color: black; */
  border-radius: 20px;
  margin-top: 5px;
  font-weight: bold;
  font-size: medium;
}
</style>
