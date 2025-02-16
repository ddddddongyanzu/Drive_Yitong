// index.js
// 获取应用实例
const app = getApp()
//引入文件  
const {
  get,
  post,
  baseurl
} = require('../../utils/request')

Page({
  data: {
    userInfo: {},
    hasUserInfo: false,
    canIUseOpenData: wx.canIUse('open-data.type.userAvatarUrl') && wx.canIUse('open-data.type.userNickName'), // 如需尝试获取用户信息可改为false
    banners: [],
    prices: {},
    movableViewX: '1000',
    movableViewY: '1000'
  },
  // 事件处理函数
  bindViewTap() {
  },
  // 用户切换页面就更新数据
  onShow: async function () {
    wx.getStorage({
      key:"user",
      success:res =>{
      console.log(res.data);
      }
    })
    let that = this;
    const res1 = await post('/banner/getAllBanner');
    console.log("--------------------用户请求了广告图片--------------------");
    console.log(res1.data);
    this.setData({
      banners: res1.data
    });

    const res2 = await post('/price/selectPrice');
    console.log("-----------------用户请求了价格--------------------");
    console.log(res2.data);
    this.setData({
      prices: res2.data
    });
  },


  onLoad: async function () {},
  scanItems: function () {
    wx.scanCode({
      complete: (res) => {
        console.log(res);
        wx.navigateTo({
          url: res["result"],
        })
      },
    })
  },
  sign(e) {
    let that = this;
    let id = e.currentTarget.dataset['index']
    console.log("--------------------用户点击了C" + id + "--------------------")
    wx.navigateTo({
      url: '../register/register',
      success: function (res) {
        // 通过eventChannel向被打开页面传送数据
        res.eventChannel.emit('acceptDataFromOpenerPage', {
          id: id
        })
      }
    });

  },
  showToast(title, icon, duration) {
    wx.showToast({
      title: title,
      icon: icon,
      duration: duration
    });
  }
})