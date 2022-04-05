import req from "./req-wrapper";

const TRAVEL_URI = {
  TRAVEL: "/travel",
};

export default {
  getTravel(success) {
    req.get(TRAVEL_URI.TRAVEL, success);
  },
};
