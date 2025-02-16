const {
  baseurl
} = require("../../../utils/request");

// pages/myRegister/myRegister.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    uId: 1,
    student: {}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let that = this;
    const eventChannel = this.getOpenerEventChannel()
    eventChannel.on('acceptDataFromOpenerPage', function (data) {
      wx.request({
        url: baseurl + '/student/selectByUId?uId=' + data.uId,
        success(res) {
          console.log(baseurl + '/student/selectByUId?uId=' + data.uId);
          console.log(res.data);
          that.setData({
            student: res.data
          });
        }
      })

    })
  },
  update() {
    wx.showToast({
      title: '请联系平台代理~',
      icon:"error"
    })
  }

})