// pages/ucenter/login/login.js
const {
  get,
  post,
  baseurl
} = require('../../../utils/request')
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },
  login: function () {
    let that = this;
    wx.getUserProfile({
      lang: "zh_CN",
      desc: '授权一键登录',
      success(res) {
        let loginUserInfo = res.userInfo;
        wx.login({
          success(res) {
            wx.request({
              url: baseurl + '/login',
              data: {
                code: res.code,
                loginUserInfo: loginUserInfo
              },
              success(res) {
                console.log("--------------------用户接受了后端传递来的登录结果--------------------");
                console.log(res);
                console.log("--------------------用户登录了，登录信息如下：--------------------");
                console.log(loginUserInfo);
                console.log("-----------用户信息--------");
                console.log(res.data.user);
                that.showToast('登录成功', 'success', 1000);
                wx.setStorageSync('user', res.data.user);
                setTimeout(() => {
                  wx.navigateBack({
                  })
                }, 300);
                
              }
            })
          }
        })
      }
    })
  },
  goBack(){
    wx.navigateBack({
      
    })
  },
  
  showToast(title, icon, duration) {
    wx.showToast({
      title: title,
      icon: icon,
      duration: duration
    });
  },
})