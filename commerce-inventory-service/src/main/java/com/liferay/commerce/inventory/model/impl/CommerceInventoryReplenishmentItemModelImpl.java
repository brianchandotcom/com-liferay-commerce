/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.commerce.inventory.model.impl;

import com.liferay.commerce.inventory.model.CommerceInventoryReplenishmentItem;
import com.liferay.commerce.inventory.model.CommerceInventoryReplenishmentItemModel;
import com.liferay.commerce.inventory.model.CommerceInventoryReplenishmentItemSoap;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the CommerceInventoryReplenishmentItem service. Represents a row in the &quot;CIReplenishmentItem&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>CommerceInventoryReplenishmentItemModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceInventoryReplenishmentItemImpl}.
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryReplenishmentItemImpl
 * @generated
 */
@JSON(strict = true)
public class CommerceInventoryReplenishmentItemModelImpl
	extends BaseModelImpl<CommerceInventoryReplenishmentItem>
	implements CommerceInventoryReplenishmentItemModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce inventory replenishment item model instance should use the <code>CommerceInventoryReplenishmentItem</code> interface instead.
	 */
	public static final String TABLE_NAME = "CIReplenishmentItem";

	public static final Object[][] TABLE_COLUMNS = {
		{"CIReplenishmentItemId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"commerceInventoryWarehouseId", Types.BIGINT}, {"sku", Types.VARCHAR},
		{"availabilityDate", Types.TIMESTAMP}, {"quantity", Types.INTEGER}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("CIReplenishmentItemId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("commerceInventoryWarehouseId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("sku", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("availabilityDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("quantity", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CIReplenishmentItem (CIReplenishmentItemId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,commerceInventoryWarehouseId LONG,sku VARCHAR(75) null,availabilityDate DATE null,quantity INTEGER)";

	public static final String TABLE_SQL_DROP =
		"drop table CIReplenishmentItem";

	public static final String ORDER_BY_JPQL =
		" ORDER BY commerceInventoryReplenishmentItem.commerceInventoryReplenishmentItemId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CIReplenishmentItem.CIReplenishmentItemId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.inventory.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.commerce.inventory.model.CommerceInventoryReplenishmentItem"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.inventory.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.commerce.inventory.model.CommerceInventoryReplenishmentItem"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.inventory.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.commerce.inventory.model.CommerceInventoryReplenishmentItem"),
		true);

	public static final long AVAILABILITYDATE_COLUMN_BITMASK = 1L;

	public static final long COMMERCEINVENTORYWAREHOUSEID_COLUMN_BITMASK = 2L;

	public static final long SKU_COLUMN_BITMASK = 4L;

	public static final long
		COMMERCEINVENTORYREPLENISHMENTITEMID_COLUMN_BITMASK = 8L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static CommerceInventoryReplenishmentItem toModel(
		CommerceInventoryReplenishmentItemSoap soapModel) {

		if (soapModel == null) {
			return null;
		}

		CommerceInventoryReplenishmentItem model =
			new CommerceInventoryReplenishmentItemImpl();

		model.setCommerceInventoryReplenishmentItemId(
			soapModel.getCommerceInventoryReplenishmentItemId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setCommerceInventoryWarehouseId(
			soapModel.getCommerceInventoryWarehouseId());
		model.setSku(soapModel.getSku());
		model.setAvailabilityDate(soapModel.getAvailabilityDate());
		model.setQuantity(soapModel.getQuantity());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<CommerceInventoryReplenishmentItem> toModels(
		CommerceInventoryReplenishmentItemSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<CommerceInventoryReplenishmentItem> models =
			new ArrayList<CommerceInventoryReplenishmentItem>(
				soapModels.length);

		for (CommerceInventoryReplenishmentItemSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.commerce.inventory.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.commerce.inventory.model.CommerceInventoryReplenishmentItem"));

	public CommerceInventoryReplenishmentItemModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _commerceInventoryReplenishmentItemId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCommerceInventoryReplenishmentItemId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _commerceInventoryReplenishmentItemId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceInventoryReplenishmentItem.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceInventoryReplenishmentItem.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CommerceInventoryReplenishmentItem, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry
				<String, Function<CommerceInventoryReplenishmentItem, Object>>
					entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceInventoryReplenishmentItem, Object>
				attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply(
					(CommerceInventoryReplenishmentItem)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CommerceInventoryReplenishmentItem, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CommerceInventoryReplenishmentItem, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CommerceInventoryReplenishmentItem)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CommerceInventoryReplenishmentItem, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CommerceInventoryReplenishmentItem, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function
		<InvocationHandler, CommerceInventoryReplenishmentItem>
			_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CommerceInventoryReplenishmentItem.class.getClassLoader(),
			CommerceInventoryReplenishmentItem.class, ModelWrapper.class);

		try {
			Constructor<CommerceInventoryReplenishmentItem> constructor =
				(Constructor<CommerceInventoryReplenishmentItem>)
					proxyClass.getConstructor(InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException roe) {
					throw new InternalError(roe);
				}
			};
		}
		catch (NoSuchMethodException nsme) {
			throw new InternalError(nsme);
		}
	}

	private static final Map
		<String, Function<CommerceInventoryReplenishmentItem, Object>>
			_attributeGetterFunctions;
	private static final Map
		<String, BiConsumer<CommerceInventoryReplenishmentItem, Object>>
			_attributeSetterBiConsumers;

	static {
		Map<String, Function<CommerceInventoryReplenishmentItem, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String,
					 Function<CommerceInventoryReplenishmentItem, Object>>();
		Map<String, BiConsumer<CommerceInventoryReplenishmentItem, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String,
					 BiConsumer<CommerceInventoryReplenishmentItem, ?>>();

		attributeGetterFunctions.put(
			"commerceInventoryReplenishmentItemId",
			new Function<CommerceInventoryReplenishmentItem, Object>() {

				@Override
				public Object apply(
					CommerceInventoryReplenishmentItem
						commerceInventoryReplenishmentItem) {

					return commerceInventoryReplenishmentItem.
						getCommerceInventoryReplenishmentItemId();
				}

			});
		attributeSetterBiConsumers.put(
			"commerceInventoryReplenishmentItemId",
			new BiConsumer<CommerceInventoryReplenishmentItem, Object>() {

				@Override
				public void accept(
					CommerceInventoryReplenishmentItem
						commerceInventoryReplenishmentItem,
					Object commerceInventoryReplenishmentItemId) {

					commerceInventoryReplenishmentItem.
						setCommerceInventoryReplenishmentItemId(
							(Long)commerceInventoryReplenishmentItemId);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<CommerceInventoryReplenishmentItem, Object>() {

				@Override
				public Object apply(
					CommerceInventoryReplenishmentItem
						commerceInventoryReplenishmentItem) {

					return commerceInventoryReplenishmentItem.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<CommerceInventoryReplenishmentItem, Object>() {

				@Override
				public void accept(
					CommerceInventoryReplenishmentItem
						commerceInventoryReplenishmentItem,
					Object companyId) {

					commerceInventoryReplenishmentItem.setCompanyId(
						(Long)companyId);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<CommerceInventoryReplenishmentItem, Object>() {

				@Override
				public Object apply(
					CommerceInventoryReplenishmentItem
						commerceInventoryReplenishmentItem) {

					return commerceInventoryReplenishmentItem.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<CommerceInventoryReplenishmentItem, Object>() {

				@Override
				public void accept(
					CommerceInventoryReplenishmentItem
						commerceInventoryReplenishmentItem,
					Object userId) {

					commerceInventoryReplenishmentItem.setUserId((Long)userId);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<CommerceInventoryReplenishmentItem, Object>() {

				@Override
				public Object apply(
					CommerceInventoryReplenishmentItem
						commerceInventoryReplenishmentItem) {

					return commerceInventoryReplenishmentItem.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<CommerceInventoryReplenishmentItem, Object>() {

				@Override
				public void accept(
					CommerceInventoryReplenishmentItem
						commerceInventoryReplenishmentItem,
					Object userName) {

					commerceInventoryReplenishmentItem.setUserName(
						(String)userName);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<CommerceInventoryReplenishmentItem, Object>() {

				@Override
				public Object apply(
					CommerceInventoryReplenishmentItem
						commerceInventoryReplenishmentItem) {

					return commerceInventoryReplenishmentItem.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<CommerceInventoryReplenishmentItem, Object>() {

				@Override
				public void accept(
					CommerceInventoryReplenishmentItem
						commerceInventoryReplenishmentItem,
					Object createDate) {

					commerceInventoryReplenishmentItem.setCreateDate(
						(Date)createDate);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<CommerceInventoryReplenishmentItem, Object>() {

				@Override
				public Object apply(
					CommerceInventoryReplenishmentItem
						commerceInventoryReplenishmentItem) {

					return commerceInventoryReplenishmentItem.getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<CommerceInventoryReplenishmentItem, Object>() {

				@Override
				public void accept(
					CommerceInventoryReplenishmentItem
						commerceInventoryReplenishmentItem,
					Object modifiedDate) {

					commerceInventoryReplenishmentItem.setModifiedDate(
						(Date)modifiedDate);
				}

			});
		attributeGetterFunctions.put(
			"commerceInventoryWarehouseId",
			new Function<CommerceInventoryReplenishmentItem, Object>() {

				@Override
				public Object apply(
					CommerceInventoryReplenishmentItem
						commerceInventoryReplenishmentItem) {

					return commerceInventoryReplenishmentItem.
						getCommerceInventoryWarehouseId();
				}

			});
		attributeSetterBiConsumers.put(
			"commerceInventoryWarehouseId",
			new BiConsumer<CommerceInventoryReplenishmentItem, Object>() {

				@Override
				public void accept(
					CommerceInventoryReplenishmentItem
						commerceInventoryReplenishmentItem,
					Object commerceInventoryWarehouseId) {

					commerceInventoryReplenishmentItem.
						setCommerceInventoryWarehouseId(
							(Long)commerceInventoryWarehouseId);
				}

			});
		attributeGetterFunctions.put(
			"sku",
			new Function<CommerceInventoryReplenishmentItem, Object>() {

				@Override
				public Object apply(
					CommerceInventoryReplenishmentItem
						commerceInventoryReplenishmentItem) {

					return commerceInventoryReplenishmentItem.getSku();
				}

			});
		attributeSetterBiConsumers.put(
			"sku",
			new BiConsumer<CommerceInventoryReplenishmentItem, Object>() {

				@Override
				public void accept(
					CommerceInventoryReplenishmentItem
						commerceInventoryReplenishmentItem,
					Object sku) {

					commerceInventoryReplenishmentItem.setSku((String)sku);
				}

			});
		attributeGetterFunctions.put(
			"availabilityDate",
			new Function<CommerceInventoryReplenishmentItem, Object>() {

				@Override
				public Object apply(
					CommerceInventoryReplenishmentItem
						commerceInventoryReplenishmentItem) {

					return commerceInventoryReplenishmentItem.
						getAvailabilityDate();
				}

			});
		attributeSetterBiConsumers.put(
			"availabilityDate",
			new BiConsumer<CommerceInventoryReplenishmentItem, Object>() {

				@Override
				public void accept(
					CommerceInventoryReplenishmentItem
						commerceInventoryReplenishmentItem,
					Object availabilityDate) {

					commerceInventoryReplenishmentItem.setAvailabilityDate(
						(Date)availabilityDate);
				}

			});
		attributeGetterFunctions.put(
			"quantity",
			new Function<CommerceInventoryReplenishmentItem, Object>() {

				@Override
				public Object apply(
					CommerceInventoryReplenishmentItem
						commerceInventoryReplenishmentItem) {

					return commerceInventoryReplenishmentItem.getQuantity();
				}

			});
		attributeSetterBiConsumers.put(
			"quantity",
			new BiConsumer<CommerceInventoryReplenishmentItem, Object>() {

				@Override
				public void accept(
					CommerceInventoryReplenishmentItem
						commerceInventoryReplenishmentItem,
					Object quantity) {

					commerceInventoryReplenishmentItem.setQuantity(
						(Integer)quantity);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getCommerceInventoryReplenishmentItemId() {
		return _commerceInventoryReplenishmentItemId;
	}

	@Override
	public void setCommerceInventoryReplenishmentItemId(
		long commerceInventoryReplenishmentItemId) {

		_commerceInventoryReplenishmentItemId =
			commerceInventoryReplenishmentItemId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public long getCommerceInventoryWarehouseId() {
		return _commerceInventoryWarehouseId;
	}

	@Override
	public void setCommerceInventoryWarehouseId(
		long commerceInventoryWarehouseId) {

		_columnBitmask |= COMMERCEINVENTORYWAREHOUSEID_COLUMN_BITMASK;

		if (!_setOriginalCommerceInventoryWarehouseId) {
			_setOriginalCommerceInventoryWarehouseId = true;

			_originalCommerceInventoryWarehouseId =
				_commerceInventoryWarehouseId;
		}

		_commerceInventoryWarehouseId = commerceInventoryWarehouseId;
	}

	public long getOriginalCommerceInventoryWarehouseId() {
		return _originalCommerceInventoryWarehouseId;
	}

	@JSON
	@Override
	public String getSku() {
		if (_sku == null) {
			return "";
		}
		else {
			return _sku;
		}
	}

	@Override
	public void setSku(String sku) {
		_columnBitmask |= SKU_COLUMN_BITMASK;

		if (_originalSku == null) {
			_originalSku = _sku;
		}

		_sku = sku;
	}

	public String getOriginalSku() {
		return GetterUtil.getString(_originalSku);
	}

	@JSON
	@Override
	public Date getAvailabilityDate() {
		return _availabilityDate;
	}

	@Override
	public void setAvailabilityDate(Date availabilityDate) {
		_columnBitmask |= AVAILABILITYDATE_COLUMN_BITMASK;

		if (_originalAvailabilityDate == null) {
			_originalAvailabilityDate = _availabilityDate;
		}

		_availabilityDate = availabilityDate;
	}

	public Date getOriginalAvailabilityDate() {
		return _originalAvailabilityDate;
	}

	@JSON
	@Override
	public int getQuantity() {
		return _quantity;
	}

	@Override
	public void setQuantity(int quantity) {
		_quantity = quantity;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), CommerceInventoryReplenishmentItem.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CommerceInventoryReplenishmentItem toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CommerceInventoryReplenishmentItem>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CommerceInventoryReplenishmentItemImpl
			commerceInventoryReplenishmentItemImpl =
				new CommerceInventoryReplenishmentItemImpl();

		commerceInventoryReplenishmentItemImpl.
			setCommerceInventoryReplenishmentItemId(
				getCommerceInventoryReplenishmentItemId());
		commerceInventoryReplenishmentItemImpl.setCompanyId(getCompanyId());
		commerceInventoryReplenishmentItemImpl.setUserId(getUserId());
		commerceInventoryReplenishmentItemImpl.setUserName(getUserName());
		commerceInventoryReplenishmentItemImpl.setCreateDate(getCreateDate());
		commerceInventoryReplenishmentItemImpl.setModifiedDate(
			getModifiedDate());
		commerceInventoryReplenishmentItemImpl.setCommerceInventoryWarehouseId(
			getCommerceInventoryWarehouseId());
		commerceInventoryReplenishmentItemImpl.setSku(getSku());
		commerceInventoryReplenishmentItemImpl.setAvailabilityDate(
			getAvailabilityDate());
		commerceInventoryReplenishmentItemImpl.setQuantity(getQuantity());

		commerceInventoryReplenishmentItemImpl.resetOriginalValues();

		return commerceInventoryReplenishmentItemImpl;
	}

	@Override
	public int compareTo(
		CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem) {

		long primaryKey = commerceInventoryReplenishmentItem.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceInventoryReplenishmentItem)) {
			return false;
		}

		CommerceInventoryReplenishmentItem commerceInventoryReplenishmentItem =
			(CommerceInventoryReplenishmentItem)obj;

		long primaryKey = commerceInventoryReplenishmentItem.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		CommerceInventoryReplenishmentItemModelImpl
			commerceInventoryReplenishmentItemModelImpl = this;

		commerceInventoryReplenishmentItemModelImpl._setModifiedDate = false;

		commerceInventoryReplenishmentItemModelImpl.
			_originalCommerceInventoryWarehouseId =
				commerceInventoryReplenishmentItemModelImpl.
					_commerceInventoryWarehouseId;

		commerceInventoryReplenishmentItemModelImpl.
			_setOriginalCommerceInventoryWarehouseId = false;

		commerceInventoryReplenishmentItemModelImpl._originalSku =
			commerceInventoryReplenishmentItemModelImpl._sku;

		commerceInventoryReplenishmentItemModelImpl._originalAvailabilityDate =
			commerceInventoryReplenishmentItemModelImpl._availabilityDate;

		commerceInventoryReplenishmentItemModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<CommerceInventoryReplenishmentItem> toCacheModel() {
		CommerceInventoryReplenishmentItemCacheModel
			commerceInventoryReplenishmentItemCacheModel =
				new CommerceInventoryReplenishmentItemCacheModel();

		commerceInventoryReplenishmentItemCacheModel.
			commerceInventoryReplenishmentItemId =
				getCommerceInventoryReplenishmentItemId();

		commerceInventoryReplenishmentItemCacheModel.companyId = getCompanyId();

		commerceInventoryReplenishmentItemCacheModel.userId = getUserId();

		commerceInventoryReplenishmentItemCacheModel.userName = getUserName();

		String userName = commerceInventoryReplenishmentItemCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceInventoryReplenishmentItemCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceInventoryReplenishmentItemCacheModel.createDate =
				createDate.getTime();
		}
		else {
			commerceInventoryReplenishmentItemCacheModel.createDate =
				Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceInventoryReplenishmentItemCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			commerceInventoryReplenishmentItemCacheModel.modifiedDate =
				Long.MIN_VALUE;
		}

		commerceInventoryReplenishmentItemCacheModel.
			commerceInventoryWarehouseId = getCommerceInventoryWarehouseId();

		commerceInventoryReplenishmentItemCacheModel.sku = getSku();

		String sku = commerceInventoryReplenishmentItemCacheModel.sku;

		if ((sku != null) && (sku.length() == 0)) {
			commerceInventoryReplenishmentItemCacheModel.sku = null;
		}

		Date availabilityDate = getAvailabilityDate();

		if (availabilityDate != null) {
			commerceInventoryReplenishmentItemCacheModel.availabilityDate =
				availabilityDate.getTime();
		}
		else {
			commerceInventoryReplenishmentItemCacheModel.availabilityDate =
				Long.MIN_VALUE;
		}

		commerceInventoryReplenishmentItemCacheModel.quantity = getQuantity();

		return commerceInventoryReplenishmentItemCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CommerceInventoryReplenishmentItem, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry
				<String, Function<CommerceInventoryReplenishmentItem, Object>>
					entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceInventoryReplenishmentItem, Object>
				attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply(
					(CommerceInventoryReplenishmentItem)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<CommerceInventoryReplenishmentItem, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry
				<String, Function<CommerceInventoryReplenishmentItem, Object>>
					entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CommerceInventoryReplenishmentItem, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply(
					(CommerceInventoryReplenishmentItem)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function
			<InvocationHandler, CommerceInventoryReplenishmentItem>
				_escapedModelProxyProviderFunction =
					_getProxyProviderFunction();

	}

	private long _commerceInventoryReplenishmentItemId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _commerceInventoryWarehouseId;
	private long _originalCommerceInventoryWarehouseId;
	private boolean _setOriginalCommerceInventoryWarehouseId;
	private String _sku;
	private String _originalSku;
	private Date _availabilityDate;
	private Date _originalAvailabilityDate;
	private int _quantity;
	private long _columnBitmask;
	private CommerceInventoryReplenishmentItem _escapedModel;

}