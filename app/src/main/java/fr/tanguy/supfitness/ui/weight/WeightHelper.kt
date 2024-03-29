package fr.tanguy.supfitness.ui.weight

import fr.tanguy.supfitness.database.Weight
import fr.tanguy.supfitness.database.WeightDao

object WeightHelper {

    private var weights = mutableListOf<Weight>()

    fun initWeight(dbWeight: List<Weight>) {
        weights = dbWeight.sortedByDescending { it.date }.toMutableList()
    }

    fun getAllWeights() = weights

    fun getSize() = weights.size

    fun getLastWeight() = weights[0].weight

    fun getMaxWeight() = weights.maxOfOrNull { it.weight!! }

    fun getMinWeight() = weights.minOfOrNull { it.weight!! }

    fun getMaxDate() = weights[0].date

    fun getMinDate() = weights[getSize() - 1].date

    fun addItem(weightDao: WeightDao, newWeight: Weight) {
        val generatedId: Long = weightDao.insertWeight(newWeight)

        val weight: Weight = newWeight
        weight.id = generatedId.toInt()

        weights.add(weight)
        weights.sortByDescending { it.date }
    }

    fun removeItem(weightDao: WeightDao, position: Int) {
        weightDao.deleteWeight(weights[position])
        weights.removeAt(position)
    }

}