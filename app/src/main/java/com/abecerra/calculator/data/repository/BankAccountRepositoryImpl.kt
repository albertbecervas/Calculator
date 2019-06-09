package com.abecerra.calculator.data.repository

import com.abecerra.calculator.data.api.dto.BankAccountDto
import com.abecerra.calculator.data.api.dto.mapper.BankAccountDtoMapper
import com.abecerra.calculator.domain.model.BankAccountModel
import com.abecerra.calculator.domain.repository.BankAccountRepository
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Single

class BankAccountRepositoryImpl : BankAccountRepository {

    override fun getBankAccount(): Single<BankAccountModel> {
        return Single.create { emitter ->
            FirebaseFirestore.getInstance().collection("AlbertID")
                .document("bankAccount")
                .get()
                .addOnSuccessListener { document ->
                    document.toObject(BankAccountDto::class.javaObjectType)?.let { bankAccount ->
                        emitter.onSuccess(BankAccountDtoMapper.map(bankAccount))
                    }
                }
                .addOnFailureListener {
                    emitter.onError(it)
                }
        }
    }

}