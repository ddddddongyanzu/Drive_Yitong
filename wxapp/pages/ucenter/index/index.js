// pages/login.js
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
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    status: {},
    role: 0,
    dialogShow: false,
    formData: [],
    lowestPrice:0,
    //暂时未校验
    rules: [{
      name: 'c1',
      rules: {
        range: [1, 2000],
        validator:(rule,value)=>{
          if(!(/(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/.test(value))){
            return rule.message
          }
        },
        message: '必须是数字'
      },
    }, {
      name: 'c2',
      rules: {
        range: [1, 2000],
        validator:(rule,value)=>{
          if(!(/(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/.test(value))){
            return rule.message
          }
        },
        message: '必须是数字'
      },
    }],
    button: [{
      text: '确定'
    }]
  },

  InputPrice: function (e) {
    const {
      field
    } = e.currentTarget.dataset
    this.setData({
      [`formData.${field}`]: e.detail.value
    })
  },

  tapDialogButton: function () {

    this.selectComponent('#form').validate((valid, errors) => {
      console.log('valid', valid, errors)
      if (!valid) {
        wx.showToast({
          title: '金额不正确！',
          icon:'error'
        })
      } else {
        console.log(parseFloat(this.data.formData.c1).toFixed(2))
        console.log((parseFloat(wx.getStorageSync('price')[0].price).toFixed(2)-this.data.lowestPrice))
        if(parseFloat(this.data.formData.c1).toFixed(2) < (parseFloat(wx.getStorageSync('price')[0].price).toFixed(2)-this.data.lowestPrice) && parseFloat(this.data.formData.c2).toFixed(2) < (parseFloat(wx.getStorageSync('price')[1].price).toFixed(2)-this.data.lowestPrice)){
          wx.showToast({
            title: '校验通过'
          })
          this.setData({
            dialogShow: false
          })
          wx.setStorageSync('discount', this.data.formData)
          wx.redirectTo({
            url: '../qrcode/qrcode',
          })
        }else{
          wx.showToast({
            title: '金额过大！',
            icon:'error'
          })
  
        }
      }
    })
    
  },

  toOrderListTap: function () {
    let that=this;
    if(this.data.hasUserInfo){
      wx.request({
        url: baseurl+'/proxy/selectBasePriceByUid',
        method:'GET',
        data:{
          uId: wx.getStorageSync('user').uId
        },
        success(res){
          that.setData({
            lowestPrice:res.data
          })
          wx.setStorageSync('lowestPrice', res.data)
        }
      })
    }
    this.setData({
      dialogShow: true
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let that = this;
    wx.getStorage({
      key: 'user',
      success(res) {
        that.setData({
          hasUserInfo: true,
          userInfo: res.data
        })
      },
      fail(res) {
        that.setData({
          hasUserInfo: false,
        })
      }
    });
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {},

  /**
   * 生命周期函数--监听页面显示  若果storage中有user 则每次打页面的时候更新一下
   */
  onShow: function () {
    var that = this;
    wx.getStorage({
      key: 'user',
      success: res => {
        console.log(res.data.uId);
        wx.request({
          url: baseurl+'/user/selectOne?id=' + res.data.uId,
          success(res2) {
            wx.setStorage({
              key: "user",
              data: res2.data
            })
            that.setData({
              userInfo: res2.data,
                hasUserInfo: true
            });
          }
        })
      }
    });
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {},

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {},

  /**
   * 用户点击右上角 和 button  实现分享
   */
  onShareAppMessage: function (res) {
    if (res.from === 'button') {
      console.log('触发页面内的分享按钮', res.target);
    } else if (res.from === 'menu') {
      console.log('触发右上角的分享按钮')
    }
    return {
      title: '点击获取优惠报名', // 转发标题
      path: '/pages/register/register', // 当前页面 path ，必须是以 / 开头的完整路径
      imageUrl: '../../../images/qrcode_icon.png'
    }
  },
  login: function () {
    // 更改为去login页面登录
      wx.navigateTo({
        url: '../login/login',
      })
  },
  showToast(title, icon, duration) {
    wx.showToast({
      title: title,
      icon: icon,
      duration: duration
    });
  },
  loginOut: function () {
    let that = this;
    wx.showModal({
      title: '注销',
      content: '是否退出登录',
      success(res) {
        if (res.confirm) {
          that.setData({
            hasUserInfo: false,
            userInfo:{}
          });
          wx.removeStorage({
            key: 'user',
          })
          that.showToast('注销成功', 'success', 1000);
          console.log("--------------------用户注销了--------------------");
        } else if (res.cancel) {}
      }
    })
  },
  // 去报名页
  myRegister() {
    let that=this;
    if (this.data.userInfo.isRegister==1) {
      wx.navigateTo({
        url: '../myRegister/myRegister',
        success: function (res) {
          // 通过eventChannel向被打开页面传送数据
          res.eventChannel.emit('acceptDataFromOpenerPage', {
            uId: that.data.userInfo.uId
          })
        }
      })
    } else {
      
      this.showToast('你已经报名了', 'error', 1000);
    }
  },
  myStudent(){
    let that = this;
    wx.request({
      url: baseurl + '/proxy/selectMyStudent?uId=' + this.data.userInfo.uId,
      success: (res) => {
        console.log(res);
        if (res.data == "") {
          that.showToast('您还没有推荐客户哟~', 'error', 1000);
        } else {
          wx.navigateTo({
            url: '../myStudent/myStudent',
            success: (res) => {
              // 通过eventChannel向被打开页面传送数据
              res.eventChannel.emit('acceptDataFromOpenerPage', {
                uId: that.data.userInfo.uId
              })
            }
          })
        }
      }
    })
  },
  bindMyHistory: function(){
    let url = '/pages/history/history';
    wx.navigateTo({
      url: url
    })
  },  
  bindMyStudy: function(){
    let url = '/pages/study/index';
    wx.navigateTo({
      url: url
    })
  },
  create_discount_link(){
    wx.navigateTo({
      url: '../link/link',
      success: (res) => {
        // 通过eventChannel向被打开页面传送数据
        res.eventChannel.emit('acceptDataFromOpenerPage', {
          // is_share: true
        })
      }
    })
  }
})