// pages/link/link.js
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
    // 优惠价格
    c1_price: "",
    c2_price: "",
    is_correct: false,
    error_message: "请输入价格",
    success_message: "",
    basePrice: 10000,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: async function (options) {
    let user = wx.getStorageSync('user');
    let basePrice = await post('/proxy/selectBasePriceByUid?uId=' + user.uId);
    console.log("此代理的底价为：" + basePrice.data);
    this.setData({
      basePrice: basePrice.data,
    });
  },
  handleInputChange(e) {
    if (e.currentTarget.dataset.modal == 1) {
      this.setData({
        c1_price: e.detail.value,
        is_correct: false
      });
    }
    if (e.currentTarget.dataset.modal == 2) {
      this.setData({
        c2_price: e.detail.value,
        is_correct: false
      });
    }
    this.check();
  },
  /**
   * 用户点击右上角 和 button  实现分享
   */
  onShareAppMessage: function () {
    // 先校验输入价格合法性。
    let user = wx.getStorageSync('user');
    return {
      title: '点击获取优惠报名', // 转发标题
      path: '/pages/register/register?c1_price=' + this.data.c1_price + '&c2_price=' + this.data.c2_price + '&uId=' + user.uId, // 当前页面 path ，必须是以 / 开头的完整路径
      imageUrl: '../../../images/qrcode_icon.png',
    }
  },

  // 发布优惠金额校验
  check() {
    let price_patt = /^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/;
    if (this.data.c1_price == "") {
      this.setData({
        error_message: '请输入C1价格~',
        c1_is_correct: false
      });
    } else if (this.data.c2_price == "") {
      this.setData({
        error_message: '请输入C2价格~',
        c2_is_correct: false
      });
    } else if (!price_patt.test(this.data.c1_price)) {
      this.setData({
        error_message: '请输入正确的C1金额~',
        is_correct: false
      });
    } else if (!price_patt.test(this.data.c2_price)) {
      this.setData({
        error_message: '请输入正确的C2金额~',
        is_correct: false
      });
    } else if (this.data.c1_price < this.data.basePrice) {
      this.setData({
        error_message: 'C1优惠价格低于底价了~',
        is_correct: false
      });
    } else if (this.data.c2_price < this.data.basePrice) {
      this.setData({
        error_message: 'C2优惠价格低于底价了~',
        is_correct: false
      });
    } else {
      this.setData({
        is_correct: true,
        success_message: "验证成功!"
      })
    }
  }
})