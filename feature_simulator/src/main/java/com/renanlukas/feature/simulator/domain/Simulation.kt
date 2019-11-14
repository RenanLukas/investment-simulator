package com.renanlukas.feature.simulator.domain

import android.os.Parcel
import android.os.Parcelable
import com.renanlukas.feature.core.presentation.parcelableCreator
import java.math.BigDecimal
import java.util.*

data class Simulation(
    val investmentParameter: InvestmentParameter,
    val grossAmount: BigDecimal,
    val taxesAmount: BigDecimal,
    val netAmount: BigDecimal,
    val grossAmountProfit: BigDecimal,
    val netAmountProfit: BigDecimal,
    val annualGrossRateProfit: BigDecimal,
    val monthlyGrossRateProfit: BigDecimal,
    val dailyGrossRateProfit: BigDecimal,
    val taxesRate: BigDecimal,
    val rateProfit: BigDecimal,
    val annualNetRateProfit: BigDecimal
) : Parcelable {

    private constructor(p: Parcel) : this(
        investmentParameter = p.readParcelable<InvestmentParameter>(
            InvestmentParameter::class.java.classLoader
        ) as InvestmentParameter,
        grossAmount = p.readSerializable() as BigDecimal,
        taxesAmount = p.readSerializable() as BigDecimal,
        netAmount = p.readSerializable() as BigDecimal,
        grossAmountProfit = p.readSerializable() as BigDecimal,
        netAmountProfit = p.readSerializable() as BigDecimal,
        annualGrossRateProfit = p.readSerializable() as BigDecimal,
        monthlyGrossRateProfit = p.readSerializable() as BigDecimal,
        dailyGrossRateProfit = p.readSerializable() as BigDecimal,
        taxesRate = p.readSerializable() as BigDecimal,
        rateProfit = p.readSerializable() as BigDecimal,
        annualNetRateProfit = p.readSerializable() as BigDecimal
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.apply {
            writeParcelable(investmentParameter, 0)
            writeSerializable(grossAmount)
            writeSerializable(taxesAmount)
            writeSerializable(netAmount)
            writeSerializable(grossAmountProfit)
            writeSerializable(netAmountProfit)
            writeSerializable(annualGrossRateProfit)
            writeSerializable(monthlyGrossRateProfit)
            writeSerializable(dailyGrossRateProfit)
            writeSerializable(taxesRate)
            writeSerializable(rateProfit)
            writeSerializable(annualNetRateProfit)
        }
    }

    override fun describeContents(): Int = 0

    companion object {
        @JvmField
        val CREATOR = parcelableCreator<Simulation>(::Simulation)
    }
}

data class InvestmentParameter(
    val investedAmount: BigDecimal,
    val yearlyInterestRate: BigDecimal,
    val maturityTotalDays: Int,
    val maturityBusinessDays: Int,
    val maturityDate: Date,
    val rate: BigDecimal,
    val isTaxFree: Boolean
) : Parcelable {

    private constructor(p: Parcel) : this(
        investedAmount = p.readSerializable() as BigDecimal,
        yearlyInterestRate = p.readSerializable() as BigDecimal,
        maturityTotalDays = p.readInt(),
        maturityBusinessDays = p.readInt(),
        maturityDate = p.readSerializable() as Date,
        rate = p.readSerializable() as BigDecimal,
        isTaxFree = p.readInt() == 1
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.apply {
            writeSerializable(investedAmount)
            writeSerializable(yearlyInterestRate)
            writeInt(maturityTotalDays)
            writeInt(maturityBusinessDays)
            writeSerializable(maturityDate)
            writeSerializable(rate)
            writeInt(if (isTaxFree) 1 else 0)
        }
    }

    override fun describeContents(): Int = 0

    companion object {
        @JvmField
        val CREATOR = parcelableCreator<InvestmentParameter>(::InvestmentParameter)
    }
}