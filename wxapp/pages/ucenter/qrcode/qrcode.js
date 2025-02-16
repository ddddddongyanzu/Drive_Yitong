// pages/qrcode/qrcode.js
import drawQrcode from '../../../utils/weapp.qrcode.esm.js'
const registerPrice='register_price'
const registerPage='../register/register'
const wxRegisterPage='https://api.phoneboss.cn'
const proxyId='uId='
const {
  requestApi
} = require('../../../utils/request')

Page({

  /**
   * 页面的初始数据
   */
  data: {
    qrcodeMode:'切换至微信二维码',
    wxMode:false,
    codeText:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // var qrText=''
    // console.log(this.getQrCodeText((price) => {
    //   console.log('---------------优惠价格-------------')
    //   console.log(price)
    //   for(let i=0 ;i<price.length;++i){
    //     qrText +=(i+1 != price.length)
    //     ?(registerPrice+i+'='+price[i]+'&')
    //     :(registerPrice+i+'='+price[i])
    //   }
    //   if(this.data.wxMode){
    //     console.log(wxRegisterPage+'?'+proxyId+this.getProxyId()+'&'+qrText)
    //     return(wxRegisterPage+'?'+proxyId+this.getProxyId()+'&'+qrText)  
    //   }else{
    //     console.log(registerPage+'?'+proxyId+this.getProxyId()+'&'+qrText)
    //     return(registerPage+'?'+proxyId+this.getProxyId()+'&'+qrText)  
    //   }
    // },[{price:99},{price:99}]))
    
    
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    this.draw();
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    requestApi('/price/selectPrice',(res)=>{
      this.setData({
        codeText:res
      })
    })
    
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
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  draw: function(){
    drawQrcode({
      width: 200,
      height: 200,
      canvasId: 'sharePriceQrcode',
      // ctx: wx.createCanvasContext('myQrcode'),
      text: this.generateQrcodeText(),
      image: {
        imageResource: '../../../images/qrcode_icon.png',
        dx: 75,
        dy: 75,
        dWidth: 45,
        dHeight: 45
      },
      callback(e) {
        console.log('e:', e);
      }
    })
  },

  getQrCodeText: function(cb,res){
    var priceArray = new Array()
    var discountArray = new Array()
    for(let value of res){
      priceArray.push(value.price)
    }
    // 待修改
    discountArray.push((parseFloat(priceArray[0])-parseFloat(wx.getStorageSync('discount').c1)).toFixed(2))
    discountArray.push((parseFloat(priceArray[1])-parseFloat(wx.getStorageSync('discount').c2)).toFixed(2))
    return typeof cb == "function" && cb(discountArray)
  },
  getProxyId:function(){
    var uId = wx.getStorageSync('user').uId;
    return uId;
  },

  callback:function(cb){

  },

  generateQrcodeText:function(){
    var qrText=''
    requestApi('/price/selectPrice',(res)=>{
      this.setData({
        codeText:res
      })
    })
    console.log(this.data.codeText)
    return(
      this.getQrCodeText((price) => {
        console.log('---------------优惠价格-------------')
        console.log(price)
        for(let i=0 ;i<price.length;++i){
          qrText +=(i+1 != price.length)
          ?(registerPrice+i+'='+price[i]+'&')
          :(registerPrice+i+'='+price[i])
        }
        if(this.data.wxMode){
          console.log(wxRegisterPage+'?'+proxyId+this.getProxyId()+'&'+qrText+'&type=wx')
          return(wxRegisterPage+'?'+proxyId+this.getProxyId()+'&'+qrText+'&type=wx')  
        }else{
          console.log(registerPage+'?'+proxyId+this.getProxyId()+'&'+qrText+'&type=mini')
          return(registerPage+'?'+proxyId+this.getProxyId()+'&'+qrText+'&type=mini')  
        }
      },this.data.codeText)
    )
  },

  download:function () {
    // 导出图片
    wx.canvasToTempFilePath({
      x: 0,
      y: 0,
      width: 300,
      height: 300,
      destWidth: 300,
      destHeight: 300,
      canvasId: 'sharePriceQrcode',
      success(res) {
        console.log('图片的临时路径为：', res.tempFilePath)
        let tempFilePath = res.tempFilePath
        // 保存图片，获取地址
        // wx.saveFile({
        //   tempFilePath,
        //   success (res) {
        //     const savedFilePath = res.savedFilePath
        //     console.log('savedFilePath', savedFilePath)
        //   }
        // })

        // 保存到相册
        wx.saveImageToPhotosAlbum({
          filePath: tempFilePath,
          success: function (res) {
            wx.showToast({
              title: '保存成功',
              icon: 'success',
              duration: 2000
            })
          },
          fail: function (res) {
            wx.showToast({
              title: '保存失败',
              icon: 'none',
              duration: 2000
            })
          }
        })
      }
    })
  },

  saveText:function(){
    this.download();
  },

  transferQrMode:function(){
    var that=this
    if(! that.data.wxMode){
      that.setData({
        qrcodeMode:'切换至小程序二维码',
        wxMode:true
      }) 
    }else{
      that.setData({
        qrcodeMode:'切换至微信二维码',
        wxMode:false
      })
    }
    that.draw()
  }
})