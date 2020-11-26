package com.csis3275.model;

import java.util.List;

public class MiniBar {

	private int itemCapacity;
	private String roomNo;
	private List<MiniBarItem> items;
	
	public MiniBar(String assignedRoomNo, int newBarCapacity)	{
		this.itemCapacity = newBarCapacity;
		this.roomNo = assignedRoomNo;
	}
	
	//Add an item
	public void addItem(MiniBarItem newItem) {
		
		try {
			//Check the capacity
			if (items.size() == this.itemCapacity)	{
				throw new Exception("The bar is full!");
			} else {
				//Add the item
				items.add(newItem);
			}
		} catch (Exception barFull)	{
			System.out.println(barFull.getMessage());
		}
		
	}
	
	//Remove and Item
	public MiniBarItem removeItem(MiniBarItem itemToRemove)	{
		
		MiniBarItem itemToReturn = new MiniBarItem();
		try {
			if (items.size() != 0)	{
				for (MiniBarItem barItem : this.items)	{
					if (itemToRemove.getName() == barItem.getName())	{
						return barItem;
					}
				}		
			} else {
			throw new Exception("The bar is empty!");
			}
			
		} catch (Exception barEmpty)	{
			System.out.println(barEmpty.getMessage());
		}
		
	}
	
	//List Items that are expired
	
	//Produce a bill
	
}
