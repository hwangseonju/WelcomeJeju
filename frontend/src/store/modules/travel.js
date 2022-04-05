import travelApi from "@/api/travel.js";

const travel = {
  namespaced: true,
  state: {
    details: [],
  },
  getters: {
    details: (state) => state.details,
  },
  mutations: {
    // state 값 바꿔줌
    setDetails(state, details) {
      state.details = details;
    },
  },
  actions: {
    FETCH_LIST({ commit }) {
      travelApi.getTravel((res) => commit("setDetails", res));
    },
  },
};

export default travel;
