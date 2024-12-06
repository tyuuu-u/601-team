<template>
	<view>
		<!-- 订单状态 -->
		<uni-title :title="orderStatus" type="h1"></uni-title>
		<view>
			<uni-card title="Delivery Information" isFull="true">
				<view><text>Time to order：{{order.created}}</text></view>
				<view v-if="order.updated!==null"><text>update time：{{order.updated}}</text></view>
				<view><text>addressee：{{order.name}}</text></view>
				<view><text>phone：{{order.phone}}</text></view>
				<view><text>address：{{order.address}}</text></view>
			</uni-card>
		</view>

		<!-- 店名 + 菜品展示{数量,单价,总价} -->
		<view>
			<uni-card :title="order.shopName" isFull="true" :extra="totalPrice" shadow="0px 0px 0px 0px" @click="goToShop">
				<view v-for="(food,i) in order.foods" :key="i">
					
				<van-card :num="food.count" :price="food.price"  :title="food.name" :thumb="food.imgSrc"></van-card>
				</view>
			</uni-card>
		</view>

		<!-- 配送详情{店主联系方式,收货人名字,收货人电话,下单时间} -->
<!-- 		<view>
			<uni-card title="配送信息" isFull="true">
				<view><text>下单时间：{{order.created}}</text></view>
				<view><text>更新时间：{{order.updated}}</text></view>
				<view><text>收货人：{{order.name}}</text></view>
				<view><text>收货人联系方式：{{order.phone}}</text></view>
				<view><text>配送地址：{{order.address}}</text></view>
			</uni-card>
		</view> -->
	</view>
</template>

<script>
	export default {
		onLoad(option) {
			this.order = JSON.parse(option.order)
			console.log(this.order);
		},
		computed: {
			orderStatus() {
				if (this.order.status === 0) return '店主正在备餐'
				if (this.order.status === 1) return '店家配送中'
				if (this.order.status === 2) return '订单已完成'
				if (this.order.status === 3) return '订单已被取消'
			},
			totalPrice(){
				return "总计：¥"+this.order.foods.reduce((total,item)=>total+=item.count*item.price,0)
			},
		},
		data() {
			return {
				order: {},
			};
		},
		methods:{
			goToShop(){
				uni.navigateTo({
					url:'../food_list/food_list?shopId='+this.order.shopId
				})
			}
		}
	}
</script>

<style lang="scss">

</style>
