<template>
	<view>
		<!-- 地址展示和选择 -->
		<select-address></select-address>
		<!-- <button @click="showCheckOut">打印结算菜单</button> -->

		<!-- 订单的菜,展示 -->
		<view v-for="(item,i) in checkOutList" :key="i">
			<van-card :num="item.count" :price="item.price" :title="item.name" :thumb="item.imgSrc" />
		</view>

		<!-- 底部总价展示,提交订单 -->
		<view>
			<van-submit-bar :price="checkOutListPrice" button-text="提交订单" @submit="onSubmit">
			</van-submit-bar>
		</view>
	</view>
</template>

<script>
	import {
		mapState,
		mapMutations,
		mapGetters
	} from 'vuex'
	export default {
		data() {
			return {

			};
		},
		computed: {
			...mapState('m_cart', ['checkOutList']),
			...mapState('m_user', ['userinfo', 'token', 'address']),
			...mapGetters('m_user', ['addstr', ]),
			...mapGetters('m_cart', ['checkOutListPrice'])
		},
		methods: {
			...mapMutations('m_cart', ['removeFoodById']),
			...mapMutations('m_user', ['updateToken']),
			showCheckOut() {
				console.log(this.checkOutList)
			},
			//提交订单给后端,要给{  name,phone,address,[{id,shopId,count},{},{}]  }
			onSubmit() {
				if (!this.addstr) return uni.$showMsg('请选择收货地址！')
				this.submitOrder()
				console.log('提交订单的菜品', this.checkOutList)
				console.log('提交用户信息', this.userinfo)
				console.log('提交用户的原地址', this.address)
				console.log('提交用户的计算属性地址', this.addstr)


				// console.log('提交订单',this.$store)
				// console.log("store test",this.$store.getters['m_user/getToken'])
				// console.log("store  muser test",this.$store.state.m_user.token)
			},

			async submitOrder() {
				const orderFoodParam = {
					name: this.address.userName,
					phone: this.address.telNumber,
					address: this.addstr,
					orderFoods: this.checkOutList
				}
				const {
					data: res
				} = await uni.$http.post("/order/create", orderFoodParam)
				if (res.code !== 200){
					this.updateToken('')
					return uni.$showMsg("创建订单错误,登录已失效,请重新登陆")
					
				}
				console.log(res)
				//清空购物车中已经结算的的菜品,根据id删除购物车的所有菜
				this.checkOutList.forEach(x => this.removeFoodById(x.id))
				//创建成功之后跳转到订单页面 switchTab
				uni.switchTab({
					url: '../../pages/order/order',
					complete: () => {
						uni.$showMsg('下单成功!',2000)
					}
				})
			}
		}
	}
</script>

<style lang="scss">

</style>
