<template>
  <a-drawer
    :title="title"
    :maskClosable="true"
    :width="drawerWidth"
    placement="right"
    :closable="true"
    @close="handleCancel"
    :visible="visible"
    style="height: calc(100% - 55px);overflow: auto;padding-bottom: 53px;">

    <template slot="title">
      <div style="width: 100%;">
        <span>{{ title }}</span>
        <span style="display:inline-block;width:calc(100% - 51px);padding-right:10px;text-align: right">
          <a-button @click="toggleScreen" icon="appstore" style="height:20px;width:20px;border:0px"></a-button>
        </span>
      </div>

    </template>

    <a-spin :spinning="confirmLoading">
      <a-form :form="form">
        <a-form-item label="运输方式" :labelCol="labelCol" :wrapperCol="wrapperCol" >
          <a-select v-decorator="[ 'transportation', validatorRules.transportation]" placeholder="请选择运输方式">
            <a-select-option value="A">空运air</a-select-option>
            <a-select-option value="S">海运sea</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="客户姓名" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input placeholder="请输入客户姓名" v-decorator="[ 'custName', validatorRules.custName]" :readOnly="!!model.id"/>
        </a-form-item>
        <a-form-item label="国家地区" :labelCol="labelCol" :wrapperCol="wrapperCol" >
          <a-select v-decorator="[ 'nationalArea', validatorRules.nationalArea]" placeholder="请选择国家地区">
            <a-select-option value="1">西马</a-select-option>
            <a-select-option value="2">新加坡</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="快递单号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input placeholder="请输入快递单号" v-decorator="[ 'expressNo', validatorRules.expressNo]" />
        </a-form-item>
        <a-form-item label="客户地址" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input placeholder="请输入客户地址" v-decorator="[ 'custAddr', validatorRules.custAddr]" />
        </a-form-item>
        <a-form-item label="联系人" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input placeholder="请输入联系人" v-decorator="[ 'contacts', validatorRules.contacts]" />
        </a-form-item>

        <a-form-item label="联系电话" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input placeholder="请输入联系电话" v-decorator="[ 'contactsNumber', validatorRules.contactsNumber]" />
        </a-form-item>
        <a-form-item label="敏感货" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select v-decorator="[ 'sensitiveGoods', validatorRules.sensitiveGoods]" placeholder="请选择敏感货">
            <a-select-option value="0">否</a-select-option>
            <a-select-option value="1">是</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="重量" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input placeholder="请输入重量" v-decorator="[ 'weight', validatorRules.weight]" />
        </a-form-item>
        <a-form-item label="长" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input placeholder="请输入长" v-decorator="[ 'len', validatorRules.len]" />
        </a-form-item>
        <a-form-item label="宽" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input placeholder="请输入宽" v-decorator="[ 'width', validatorRules.width]" />
        </a-form-item>
        <a-form-item label="高" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input placeholder="请输入高" v-decorator="[ 'height', validatorRules.height]" />
        </a-form-item>
        <a-form-item label="体积" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input placeholder="请输入体积" v-decorator="[ 'volumeLenWidthHeight', validatorRules.volumeLenWidthHeight]" />
        </a-form-item>
        <a-form-item label="体积重" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input placeholder="请输入体积重" v-decorator="[ 'volumeWeight', validatorRules.volumeWeight]" />
        </a-form-item>

        <a-form-item label="运单号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input placeholder="请输入运单号" v-decorator="[ 'trackingNumber', validatorRules.trackingNumber]" />
        </a-form-item>
        <a-form-item label="派送公司" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input placeholder="请输入派送公司" v-decorator="[ 'deliverCompany', validatorRules.deliverCompany]" />
        </a-form-item>
        <a-form-item label="运费单价" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input placeholder="请输入运费单价" v-decorator="[ 'freightUnitPrice', validatorRules.freightUnitPrice]" />
        </a-form-item>
        <a-form-item label="运费" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input placeholder="请输入运费" v-decorator="[ 'totalAmount', validatorRules.totalAmount]" />
        </a-form-item>
        <a-form-item label="客户付款时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input placeholder="请输入客户付款时间" v-decorator="[ 'custPayDate', validatorRules.custPayDate]" />
        </a-form-item>
        <a-form-item label="客户付款渠道" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select v-decorator="[ 'custPayChannel', {}]" placeholder="请选择客户付款渠道">
            <a-select-option value="11">微信转账</a-select-option>
            <a-select-option value="12">微信红包</a-select-option>
            <a-select-option value="21">支付宝</a-select-option>
            <a-select-option value="31">马币</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="客户结算渠道" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select v-decorator="[ 'custSettlement', {}]" placeholder="请选择客户结算渠道">
            <a-select-option value="11">微信转账</a-select-option>
            <a-select-option value="12">微信红包</a-select-option>
            <a-select-option value="21">支付宝</a-select-option>
            <a-select-option value="31">马币</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="马币汇率" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input placeholder="请输入马币汇率" v-decorator="[ 'exchangeRate', validatorRules.exchangeRate]" />
        </a-form-item>
        <a-form-item label="公司结算渠道" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select v-decorator="[ 'companySettlement', {}]" placeholder="请选择公司结算渠道">
            <a-select-option value="11">微信转账</a-select-option>
            <a-select-option value="12">微信红包</a-select-option>
            <a-select-option value="21">支付宝</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="公司结算渠道日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input placeholder="请输入公司结算渠道日期" v-decorator="[ 'companySettlementDate', validatorRules.companySettlementDate]" />
        </a-form-item>
        <a-form-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input placeholder="请输入备注" v-decorator="[ 'remark', validatorRules.remark]" />
        </a-form-item>

      </a-form>
    </a-spin>
    <depart-window ref="departWindow" @ok="modalFormOk"></depart-window>

    <div class="drawer-bootom-button" v-show="!disableSubmit">
      <a-popconfirm title="确定放弃编辑？" @confirm="handleCancel" okText="确定" cancelText="取消">
        <a-button style="margin-right: .8rem">取消</a-button>
      </a-popconfirm>
      <a-button @click="handleSubmit" type="primary" :loading="confirmLoading">提交</a-button>
    </div>
  </a-drawer>
</template>

<script>
  import pick from 'lodash.pick'
  import moment from 'moment'
  import Vue from 'vue'
  // 引入搜索部门弹出框的组件
  import departWindow from './DepartWindow'
  import JSelectPosition from '@/components/jeecgbiz/JSelectPosition'
  import { ACCESS_TOKEN } from "@/store/mutation-types"
  import { getAction } from '@/api/manage'
  import { addBill,editBill } from '@/api/api'
  import { disabledAuthFilter } from "@/utils/authFilter"
  import {duplicateCheck } from '@/api/api'

  export default {
    name: "BillModal",
    components: {
      departWindow,
      JSelectPosition
    },
    data () {
      return {
        departDisabled: false, //是否是我的部门调用该页面
        roleDisabled: false, //是否是角色维护调用该页面
        modalWidth:800,
        drawerWidth:700,
        modaltoggleFlag:true,
        confirmDirty: false,
        selectedDepartKeys:[], //保存用户选择部门id
        checkedDepartKeys:[],
        checkedDepartNames:[], // 保存部门的名称 =>title
        checkedDepartNameString:"", // 保存部门的名称 =>title
        userId:"", //保存用户id
        disableSubmit:false,
        userDepartModel:{userId:'',departIdList:[]}, // 保存SysUserDepart的用户部门中间表数据需要的对象
        dateFormat:"YYYY-MM-DD",
        validatorRules:{
          transportation:{rules: [{ required: true, message: '请选择运输方式!'}]},
          custName:{rules: [{required: true, message: '请输入客户姓名!'}]},
          nationalArea:{rules: [{ required: true, message: '请选择国家地区!'}]},
          expressNo:{rules: [{ required: true, message: '请输入快递单号!'}]},
          custAddr:{rules: [{ required: true, message: '请输入客户地址!'}]},
          contacts:{rules: [{ required: true, message: '请输入联系人!'}]},
          contactsNumber:{rules: [{ required: true, message: '请输入联系电话!'}]},
          sensitiveGoods:{rules: [{ required: true, message: '请选择敏感货!'}]}
        },
        title:"操作",
        visible: false,
        model: {},
        roleList:[],
        selectedRole:[],
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        uploadLoading:false,
        confirmLoading: false,
        headers:{},
        form:this.$form.createForm(this),
        picUrl: "",
        url: {
          fileUpload: window._CONFIG['domianURL']+"/sys/common/upload",
          imgerver: window._CONFIG['domianURL']+"/sys/common/view",
          userWithDepart: "/sys/user/userDepartList", // 引入为指定用户查看部门信息需要的url
          userId:"/sys/user/generateUserId", // 引入生成添加用户情况下的url
          syncUserByUserName:"/process/extActProcess/doSyncUserByUserName",//同步用户到工作流
        },
      }
    },
    created () {
      const token = Vue.ls.get(ACCESS_TOKEN);
      this.headers = {"X-Access-Token":token}

    },
    computed:{
      uploadAction:function () {
        return this.url.fileUpload;
      }
    },
    methods: {
      isDisabledAuth(code){
        return disabledAuthFilter(code);
      },
      //窗口最大化切换
      toggleScreen(){
        if(this.modaltoggleFlag){
          this.modalWidth = window.innerWidth;
        }else{
          this.modalWidth = 800;
        }
        this.modaltoggleFlag = !this.modaltoggleFlag;
      },
      initialRoleList(){

      },
      refresh () {
          this.selectedDepartKeys=[];
          this.checkedDepartKeys=[];
          this.checkedDepartNames=[];
          this.checkedDepartNameString = "";
          this.userId=""
      },
      add () {
        this.picUrl = "";
        this.refresh();
        this.edit({activitiSync:'1'});
      },
      edit (record) {
        this.resetScreenSize(); // 调用此方法,根据屏幕宽度自适应调整抽屉的宽度
        let that = this;
        that.initialRoleList();
        that.checkedDepartNameString = "";
        that.form.resetFields();

        that.userId = record.id;
        that.visible = true;
        that.model = Object.assign({}, record);
        that.$nextTick(() => {
          that.form.setFieldsValue(pick(this.model,'transportation','custName','nationalArea','expressNo','custAddr','contacts','contactsNumber','sensitiveGoods','volumeLenWidthHeight','volumeWeight','trackingNumber','deliverCompany','freightUnitPrice','totalAmount','custPayDate','custPayChannel','custSettlement','exchangeRate','companySettlement','companySettlementDate','remark'))
        });
        // 调用查询用户对应的部门信息的方法
        that.checkedDepartKeys = [];
        that.loadCheckedDeparts();
      },
      //
      loadCheckedDeparts(){
        let that = this;
        if(!that.userId){return}
        getAction(that.url.userWithDepart,{userId:that.userId}).then((res)=>{
          that.checkedDepartNames = [];
          if(res.success){
            for (let i = 0; i < res.result.length; i++) {
              that.checkedDepartNames.push(res.result[i].title);
              this.checkedDepartNameString = this.checkedDepartNames.join(",");
              that.checkedDepartKeys.push(res.result[i].key);
            }
            that.userDepartModel.departIdList = that.checkedDepartKeys
          }else{
            console.log(res.message);
          }
        })
      },
      close () {
        this.$emit('close');
        this.visible = false;
        this.disableSubmit = false;
        this.selectedRole = [];
        this.userDepartModel = {userId:'',departIdList:[]};
        this.checkedDepartNames = [];
        this.checkedDepartNameString='';
        this.checkedDepartKeys = [];
        this.selectedDepartKeys = [];
      },
      moment,
      handleSubmit () {

        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let formData = Object.assign(this.model, values);
            let obj;
            if(!this.model.id){
              formData.id = this.userId;
              obj=addBill(formData);
            }else{
              obj=editBill(formData);
            }
            obj.then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.checkedDepartNames = [];
              that.userDepartModel.departIdList = {userId:'',departIdList:[]};

              that.close();
            })

          }
        })
      },
      handleCancel () {
        this.close()
      },
      validateToNextPassword  (rule, value, callback) {
        const form = this.form;
        const confirmpassword=form.getFieldValue('confirmpassword');

        if (value && confirmpassword && value !== confirmpassword) {
          callback('两次输入的密码不一样！');
        }
        if (value && this.confirmDirty) {
          form.validateFields(['confirm'], { force: true })
        }
        callback();
      },
      compareToFirstPassword  (rule, value, callback) {
        const form = this.form;
        if (value && value !== form.getFieldValue('password')) {
          callback('两次输入的密码不一样！');
        } else {
          callback()
        }
      },
      validatePhone(rule, value, callback){
        if(!value){
          callback()
        }else{
          //update-begin--Author:kangxiaolin  Date:20190826 for：[05] 手机号不支持199号码段--------------------
          if(new RegExp(/^1[3|4|5|7|8|9][0-9]\d{8}$/).test(value)){
            //update-end--Author:kangxiaolin  Date:20190826 for：[05] 手机号不支持199号码段--------------------

            var params = {
              tableName: 'sys_user',
              fieldName: 'phone',
              fieldVal: value,
              dataId: this.userId
            };
            duplicateCheck(params).then((res) => {
              if (res.success) {
                callback()
              } else {
                callback("手机号已存在!")
              }
            })
          }else{
            callback("请输入正确格式的手机号码!");
          }
        }
      },
      validateEmail(rule, value, callback){
        if(!value){
          callback()
        }else{
          if(new RegExp(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/).test(value)){
            var params = {
              tableName: 'sys_user',
              fieldName: 'email',
              fieldVal: value,
              dataId: this.userId
            };
            duplicateCheck(params).then((res) => {
              console.log(res)
              if (res.success) {
                callback()
              } else {
                callback("邮箱已存在!")
              }
            })
          }else{
            callback("请输入正确格式的邮箱!")
          }
        }
      },
      validateUsername(rule, value, callback){
        var params = {
          tableName: 'sys_user',
          fieldName: 'username',
          fieldVal: value,
          dataId: this.userId
        };
        duplicateCheck(params).then((res) => {
          if (res.success) {
          callback()
        } else {
          callback("用户名已存在!")
        }
      })
      },
      validateWorkNo(rule, value, callback){
        var params = {
          tableName: 'sys_user',
          fieldName: 'work_no',
          fieldVal: value,
          dataId: this.userId
        };
        duplicateCheck(params).then((res) => {
          if (res.success) {
            callback()
          } else {
            callback("工号已存在!")
          }
        })
      },
      handleConfirmBlur  (e) {
        const value = e.target.value;
        this.confirmDirty = this.confirmDirty || !!value
      },

      normFile  (e) {
        console.log('Upload event:', e);
        if (Array.isArray(e)) {
          return e
        }
        return e && e.fileList
      },
      beforeUpload: function(file){
        var fileType = file.type;
        if(fileType.indexOf('image')<0){
          this.$message.warning('请上传图片');
          return false;
        }
        //TODO 验证文件大小
      },
      handleChange (info) {
        this.picUrl = "";
        if (info.file.status === 'uploading') {
          this.uploadLoading = true;
          return
        }
        if (info.file.status === 'done') {
          var response = info.file.response;
          this.uploadLoading = false;
          console.log(response);
          if(response.success){
            this.model.avatar = response.message;
            this.picUrl = "Has no pic url yet";
          }else{
            this.$message.warning(response.message);
          }
        }
      },
      getAvatarView(){
        return this.url.imgerver +"/"+ this.model.avatar;
      },
      // 搜索用户对应的部门API
      onSearch(){
        this.$refs.departWindow.add(this.checkedDepartKeys,this.userId);
      },

      // 获取用户对应部门弹出框提交给返回的数据
      modalFormOk (formData) {
        this.checkedDepartNames = [];
        this.selectedDepartKeys = [];
        this.checkedDepartNameString = '';
        this.userId = formData.userId;
        this.userDepartModel.userId = formData.userId;
        for (let i = 0; i < formData.departIdList.length; i++) {
          this.selectedDepartKeys.push(formData.departIdList[i].key);
          this.checkedDepartNames.push(formData.departIdList[i].title);
          this.checkedDepartNameString = this.checkedDepartNames.join(",");
        }
        this.userDepartModel.departIdList = this.selectedDepartKeys;
        this.checkedDepartKeys = this.selectedDepartKeys  //更新当前的选择keys
       },
      // 根据屏幕变化,设置抽屉尺寸
      resetScreenSize(){
        let screenWidth = document.body.clientWidth;
        if(screenWidth < 500){
          this.drawerWidth = screenWidth;
        }else{
          this.drawerWidth = 700;
        }
      },
    }
  }
</script>

<style scoped>
  .avatar-uploader > .ant-upload {
    width:104px;
    height:104px;
  }
  .ant-upload-select-picture-card i {
    font-size: 49px;
    color: #999;
  }

  .ant-upload-select-picture-card .ant-upload-text {
    margin-top: 8px;
    color: #666;
  }

  .ant-table-tbody .ant-table-row td{
    padding-top:10px;
    padding-bottom:10px;
  }

  .drawer-bootom-button {
    position: absolute;
    bottom: -8px;
    width: 100%;
    border-top: 1px solid #e8e8e8;
    padding: 10px 16px;
    text-align: right;
    left: 0;
    background: #fff;
    border-radius: 0 0 2px 2px;
  }
</style>